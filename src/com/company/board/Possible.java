package com.company.board;

import java.util.ArrayList;
import static com.company.lookup.Pieces.*;

public class Possible {

    static void white(byte[][] board, int[] move, ArrayList<int[]> moves, int x, int y, int X, int Y) {
        for(int i = 1; i < Math.min(X, Y) + 1; i++) {
            if (board[x + move[0] * i][y + move[1] * i] == 0) {
                moves.add(new int[]{x, y, x + move[0] * i, y + move[1] * i});
            }
            else if (board[x + move[0] * i][y + move[1] * i] > 6) {
                moves.add(new int[]{x, y, x + move[0] * i, y + move[1] * i});
                break;
            } else break;
        }
    }

    static void black(byte[][] board, int[] move, ArrayList<int[]> moves, int x, int y, int X, int Y) {
        for(int i = 1; i < Math.min(X, Y) + 1; i++) {
            if (board[x + move[0] * i][y + move[1] * i] == 0) {
                moves.add(new int[]{x, y, x + move[0] * i, y + move[1] * i});
            }
            else if (board[x + move[0] * i][y + move[1] * i] < 7) {
                moves.add(new int[]{x, y, x + move[0] * i, y + move[1] * i});
                break;
            } else break;
        }
    }

    public static ArrayList<int[]> possible(byte[][] board, boolean[] data) {
        ArrayList<int[]> moves = new ArrayList();
        for(int x = 0; x < board.length; x++) { for(int y = 0; y < board[0].length; y++) {
            byte square = board[x][y];
            if(data[0]) {
                if(square == 0 || square > 6) continue;
                switch (square) {
                    case WHITE_PAWN:
                        if(y < board.length - 1) {
                            if (board[x][y + 1] == 0) {
                                moves.add(new int[]{x, y, x, y + 1});
                                if (y == 1 && board[x][y + 2] == 0) moves.add(new int[]{x, y, x, y + 2});
                            }
                            if (x < board.length - 1 && board[x + 1][y + 1] > 6)
                                moves.add(new int[]{x, y, x + 1, y + 1});
                            if (x > 0 && board[x - 1][y + 1] > 6) moves.add(new int[]{x, y, x - 1, y + 1});
                        }
                        break;
                    case WHITE_ROOK:
                        for(int[] move : ROOK) {
                            int X = move[0] == 0 ? 10 : move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 0 ? 10 : move[1] == 1 ? (board.length - 1) - y : y;
                            white(board, move, moves, x, y, X, Y);
                        }
                        break;
                    case WHITE_BISHOP:
                        for(int[] move : BISHOP) {
                            int X = move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 1 ? (board.length - 1) - y : y;
                            white(board, move, moves, x, y, X, Y);
                        }
                        break;
                    case WHITE_KNIGHT:
                        for(int[] move : KNIGHT) {
                            if(x + move[0] >= 0 && x + move[0] < board.length && y + move[1] >= 0 && y + move[1] < board[0].length) {
                                if (board[x + move[0]][y + move[1]] > 6 || board[x + move[0]][y + move[1]] == 0) {
                                    moves.add(new int[]{x, y, x + move[0], y + move[1]});
                                }
                            }
                        }
                        break;
                    case WHITE_KING:
                        for (int[] move : KING){
                            if(x + move[0] < board.length && x + move[0] >= 0 && y + move[1] < board[0].length && y + move[1] >= 0) {
                                if (board[x + move[0]][y + move[1]] == 0) {
                                    moves.add(new int[]{x, y, x + move[0], y + move[1]});
                                } else if (board[x + move[0]][y + move[1]] > 6) {
                                    moves.add(new int[]{x, y, x + move[0], y + move[1]});
                                }
                            }
                        }
                        break;
                    case WHITE_QUEEN:
                        for(int[] move : QUEEN) {
                            int X = move[0] == 0 ? 10 : move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 0 ? 10 : move[1] == 1 ? (board.length - 1) - y : y;
                            white(board, move, moves, x, y, X, Y);
                        }
                        break;

                }
            }
            else {
                if(square == 0 || square < 7) continue;
                switch (square) {
                    case BLACK_PAWN:
                        if(y > 0) {
                            if (board[x][y - 1] == 0) {
                                moves.add(new int[]{x, y, x, y - 1});
                                if (y == board.length - 2 && board[x][y - 2] == 0) moves.add(new int[]{x, y, x, y - 2});
                            }
                            if (x < board.length - 1 && board[x + 1][y - 1] < 7 && board[x + 1][y - 1] > 0)
                                moves.add(new int[]{x, y, x + 1, y - 1});
                            if (x > 0 && board[x - 1][y - 1] < 7 && board[x - 1][y - 1] > 0)
                                moves.add(new int[]{x, y, x - 1, y - 1});
                        }
                        break;
                    case BLACK_ROOK:
                        for(int[] move : ROOK) {
                            int X = move[0] == 0 ? 10 : move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 0 ? 10 : move[1] == 1 ? (board.length - 1) - y : y;
                            black(board, move, moves, x, y, X, Y);
                        }
                        break;
                    case BLACK_BISHOP:
                        for(int[] move : BISHOP) {
                            int X = move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 1 ? (board.length - 1) - y : y;
                            black(board, move, moves, x, y, X, Y);
                        }
                        break;
                    case BLACK_KNIGHT:
                        for(int[] move : KNIGHT) {
                            if(x + move[0] >= 0 && x + move[0] < board.length && y + move[1] >= 0 && y + move[1] < board[0].length) {
                                if (board[x + move[0]][y + move[1]] < 7 || board[x + move[0]][y + move[1]] == 0) {
                                    moves.add(new int[]{x, y, x + move[0], y + move[1]});
                                }
                            }
                        }
                        break;
                    case BLACK_KING:
                        for (int[] move : KING){
                            if(x + move[0] < board.length && x + move[0] >= 0 && y + move[1] < board[0].length && y + move[1] >= 0) {
                                if (board[x + move[0]][y + move[1]] < 7) {
                                    moves.add(new int[]{x, y, x + move[0], y + move[1]});
                                }
                            }
                        }
                        break;
                    case BLACK_QUEEN:
                        for(int[] move : QUEEN) {
                            int X = move[0] == 0 ? 10 : move[0] == 1 ? (board.length - 1) - x : x;
                            int Y = move[1] == 0 ? 10 : move[1] == 1 ? (board.length - 1) - y : y;
                            black(board, move, moves, x, y, X, Y);
                        }
                        break;
                }
            }
        }}
        return moves;
    }
}
