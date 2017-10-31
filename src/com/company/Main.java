package com.company;

import com.company.board.Possible;
import com.company.board.Value;

import java.util.*;
import java.util.concurrent.*;
import static com.company.Save.*;
import static com.company.MinMax.*;


public class Main {

    static ExecutorService ES;

    static Queue<Node> toExpand;
    static byte[][] BOARD;
    static boolean[] DATA = {true};

    static char[] chars = new char[] { '/', '-', '\\', '/', '-', '\\'};

    static Node parent;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("------------------------------------------------------------");
        System.out.println("  JChess v0.3");
        System.out.println("------------------------------------------------------------");
        BOARD = load().get(1);
        System.out.println(Value.value(BOARD));
        GUI gui = new GUI(BOARD, Possible.possible(BOARD, DATA));
        System.out.println(Value.value(BOARD));
        for(int n = 0; n < 100000; n++) {
            iterate();
            gui.update(BOARD, DATA, Possible.possible(BOARD, DATA));
        }
    }

    static void iterate() throws InterruptedException {
        parent = new Node(BOARD, DATA);
        ES = Executors.newFixedThreadPool(8);
        toExpand = new LinkedBlockingQueue();
        toExpand.add(parent);
        long time = System.currentTimeMillis();
        int i = 0;
        while (i < 100000) {
            while (toExpand.size() == 0) Thread.sleep(10);
            ES.execute(new Expander(toExpand.poll()));
            i++;
        }
        System.out.println(i + " operations started. Waiting for completion... ");
        ES.shutdown();
        System.out.print("/");
        while (!ES.awaitTermination(10, TimeUnit.MILLISECONDS)) {
            System.out.print("\r");
            System.out.print(chars[i++ % 3]);
        }
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println("RESULT:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Total time: " + (System.currentTimeMillis() - time));
        System.out.println("End-nodes: " + toExpand.size());
        System.out.println("------------------------------------------------------------");
        ArrayList<Node> temp = new ArrayList<Node>();
        Collections.addAll(temp, parent.children);
        Collections.shuffle(temp);
        System.out.println(temp);
        parent.children = temp.toArray(new Node[temp.size()]);
        Arrays.sort(parent.children, (a, b) -> DATA[0] ? find(b) - find(a) : -(find(b) - find(a)));
        String output = "";
        for (int k = 0; k < 3; k++) {
            Node node = parent.children[k];
            System.out.println("Value: " + find(node));
            System.out.println("------------------------------------------------------------");
            for (byte[] col : node.position) {
                for (byte s : col) System.out.print(s + " ");
                System.out.println();
            }
            System.out.println("------------------------------------------------------------");
        }
        System.out.println(output);

        BOARD = parent.children[0].position;
        DATA = parent.children[0].data;
    }

}


