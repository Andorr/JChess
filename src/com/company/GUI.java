package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame {

    Board b;
    ArrayList<int[]> p;

    GUI(byte[][] board, ArrayList<int[]> p) {
        this.b = new Board(board);
        this.p = p;
        this.add(b);
        setSize(800, 800);
        setVisible(true);

    }

    void update(byte[][] board, boolean[] data, ArrayList<int[]> p) {
        this.p = p;
        b.update2(board);
        b.update(b.getGraphics());
    }

    class Board extends JPanel {
        byte[][] board;
        Board(byte[][] board) {
            this.board = board;
        }

        void update2(byte[][] board) {
            this.board = board;
            update(this.getGraphics());
        }

        protected void paintComponent(Graphics g) {
            BufferedImage[] sprites;
            try {
                BufferedImage image = ImageIO.read(new File("img/sprite.png"));
                final int width = image.getWidth() / 6;
                final int height = image.getHeight() / 2;
                final int rows = 2;
                final int cols = 6;
                sprites = new BufferedImage[rows * cols];

                for (int i = 0; i < rows; i++)
                {
                    for (int j = 0; j < cols; j++)
                    {
                        sprites[(i * cols) + j] = image.getSubimage(
                                j * width,
                                i * height,
                                width,
                                height
                        );
                    }
                }

                int SCALE = getWidth() / 8;
                boolean square = true;
                for(int i = 0; i < 8; i++) {
                    square = !square;
                    for(int j = 0; j < 8; j++) {
                        g.setColor(square ? Color.GRAY : Color.white);
                        for(int[] m : p) {
                            if(m[2] == i && m[3] == j) {
                                g.setColor(Color.blue);
                            }
                        }
                        g.fillRect(i * SCALE, j * SCALE, SCALE, SCALE);
                        int index;
                        switch (board[i][j]) {
                            case 1:
                                index = 5;
                                break;
                            case 2:
                                index = 3;
                                break;
                            case 3:
                                index = 2;
                                break;
                            case 4:
                                index = 4;
                                break;
                            case 5:
                                index = 1;
                                break;
                            case 6:
                                index = 0;
                                break;
                            case 7:
                                index = 11;
                                break;
                            case 8:
                                index = 9;
                                break;
                            case 9:
                                index = 8;
                                break;
                            case 10:
                                index = 10;
                                break;
                            case 11:
                                index = 7;
                                break;
                            case 12:
                                index = 6;
                                break;
                            default:
                                index = -1;
                                break;
                        }
                        if(index > -1) g.drawImage(sprites[index], i * SCALE, j * SCALE, SCALE, SCALE, this);
                        square = !square;
                    }
                }

            } catch (IOException e) {
                System.out.println("bob");
            }

        }
    }
}
