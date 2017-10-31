package com.company;

import com.company.board.Possible;
import com.company.board.Value;

import java.util.ArrayList;
import java.util.Arrays;

import static com.company.lookup.Pieces.*;

public class Node {

    final byte[][] position;
    final boolean[] data;

    Node[] children;

    boolean unchanged = false;
    int last_value;

    Node(byte[][] position, boolean[] data) {
        this.position = position;
        this.data = data;
    }

    int value() {
        return Value.value(position);
    }

    void expand(int loop) {
        unchanged = false;
        if(!(Math.abs(value()) > 50000)) {
            ArrayList<Node> c = new ArrayList();
            for (int[] move : Possible.possible(position, data)) {
                byte[][] newPosition = new byte[8][8];
                for (int i = 0; i < position.length; i++)
                    newPosition[i] = Arrays.copyOf(position[i], position[i].length);

                boolean expand = false;
                if (newPosition[move[2]][move[3]] > 0) {
                    expand = true;
                }
                newPosition[move[2]][move[3]] = newPosition[move[0]][move[1]];
                newPosition[move[0]][move[1]] = 0;

                for (int i = 0; i < position.length; i++) {
                    if (position[i][0] == BLACK_PAWN) position[i][0] = BLACK_QUEEN;
                    if (position[i][position.length - 1] == WHITE_PAWN) position[i][position.length - 1] = WHITE_QUEEN;
                }

                boolean[] newData = Arrays.copyOf(data, data.length);
                newData[0] = !newData[0];
                Node child = new Node(newPosition, newData);
                if (expand && loop < 1) child.expand(++loop);
                c.add(child);
            }
            children = c.toArray(new Node[c.size()]);
        }

    }

    public String toString() {
        return "Node (value = " + value() + ")";
    }
}