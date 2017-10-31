package com.company;

import java.io.*;
import java.util.ArrayList;

public class Save {

    public static void save(byte[][] board) {

        // The name of the file to open.
        String fileName = "saves.data";

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
            for(int x = 0; x < board.length; x++) {
                for(int y = 0; y < board[0].length; y++) {
                    bufferedWriter.write(board[x][y] + ",");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }

    public static ArrayList<byte[][]> load() {
        String fileName = "saves.data";
        ArrayList<byte[][]> boards = new ArrayList();
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while((line = bufferedReader.readLine()) != null) {
                byte[][] board = new byte[8][8];
                int x = 0;
                int y = -1;
                for (String square : line.split(",")) {
                    if(y++ > 6) {
                        y = 0;
                        x++;
                        if(x > 7) break;
                    }
                    board[x][y] = Byte.parseByte(square);
                }
                boards.add(board);
            }
            bufferedReader.close();
            return boards;
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
        return null;
    }

}
