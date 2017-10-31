package com.company.board;

import static com.company.lookup.Pieces.*;

public class Value {

    public static int value(byte[][] position) {
        int v = 0;
        for(int i = 0; i < position.length; i++) {
            for (int j = 0; j < position.length; j++) {
                v += VALUES[position[i][j]];
            }
        }
        v += development(position);
        return v;
    }

    private static int white(byte[][] board, int[] move, int moves, int x, int y, int X, int Y) {
        for(int i = 1; i < Math.min(X, Y) + 1; i++) {
            if (board[x + move[0] * i][y + move[1] * i] == 0) {
                moves++;
            }
            else {
                moves++;
                break;
            }
        }

        return moves;
    }

    private static int black(byte[][] board, int[] move, int moves, int x, int y, int X, int Y) {
        for(int i = 1; i < Math.min(X, Y) + 1; i++) {
            if (board[x + move[0] * i][y + move[1] * i] == 0) {
                moves--;
            }
            else {
                moves--;
                break;
            }
        }
        return moves;
    }

    private static int development(byte[][] board) {
        int moves = 0;
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                switch(board[x][y]) {

                    case WHITE_PAWN:
                        if (y < board.length - 2) {
                            if (x < board.length - 1 && board[x + 1][y + 1] > 0) moves++;
                            if (x > 0 && board[x - 1][y + 1] > 0) moves ++;
                            moves += y;
                        }
                        break;

                    case WHITE_KNIGHT:
                        if(y == 0) moves += 10;
                        for (int[] move : KNIGHT) {
                            if (x + move[0] >= 0 && x + move[0] < board.length && y + move[1] >= 0 && y + move[1] < board[0].length) {
                                moves++;
                            }
                        }
                        break;

                    case WHITE_BISHOP:
                        if(y == 0) moves += 10;
                        for (int[] move : BISHOP) {
                            int X = move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 1 ? (board.length - 1) - y : y;
                            moves = white(board, move, moves, x, y, X, Y);
                        }
                        break;

                    case WHITE_ROOK:
                        for(int[] move : ROOK) {
                            int X = move[0] == 0 ? 10 : move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 0 ? 10 : move[1] == 1 ? (board.length - 1) - y : y;
                            //moves += white(board, move, moves, x, y, X, Y);
                        }
                        break;

                    case WHITE_KING:
                            for (int[] move : KING) {
                                if (x + move[0] < board.length && x + move[0] >= 0 && y + move[1] < board[0].length && y + move[1] >= 0) {
                                    if (board[x + move[0]][y + move[1]] != 0) {
                                        moves += 3;
                                    }
                                }
                            }
                        break;

                    case BLACK_PAWN:
                        if (y > 0) {
                            if (x > 0 && board[x - 1][y - 1] > 0) moves--;
                            if (x < board.length - 2 && board[x + 1][y - 1] > 0) moves--;
                            moves -= 7 - y;
                        }
                        break;

                    case BLACK_KNIGHT:
                        for (int[] move : KNIGHT) {
                            if(y == board.length - 1) moves -= 10;
                            if (x + move[0] >= 0 && x + move[0] < board.length && y + move[1] >= 0 && y + move[1] < board[0].length) {
                                moves--;
                            }
                        }
                        break;

                    case BLACK_BISHOP:
                        if(y == board.length - 1) moves -= 10;
                        for (int[] move : BISHOP) {
                            int X = move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 1 ? (board.length - 1) - y : y;
                            moves = black(board, move, moves, x, y, X, Y);
                        }
                        break;
                    case BLACK_ROOK:
                        for(int[] move : ROOK) {
                            int X = move[0] == 0 ? 10 : move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 0 ? 10 : move[1] == 1 ? (board.length - 1) - y : y;
                            //moves = black(board, move, moves, x, y, X, Y);
                        }
                        break;

                    case BLACK_KING:
                        for (int[] move : KING) {
                            if (x + move[0] < board.length && x + move[0] >= 0 && y + move[1] < board[0].length && y + move[1] >= 0) {
                                if (board[x + move[0]][y + move[1]] != 0) {
                                    moves -= 3;
                                }
                            }
                        }
                        break;

                 }
                }
            }

        return moves;
    }
}
