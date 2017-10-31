package com.company.lookup;

public class Pieces {
    public static final byte WHITE_PAWN = 1;
    public static final byte WHITE_KNIGHT = 2;
    public static final byte WHITE_BISHOP = 3;
    public static final byte WHITE_ROOK = 4;
    public static final byte WHITE_QUEEN = 5;
    public static final byte WHITE_KING = 6;

    public static final byte BLACK_PAWN = 7;
    public static final byte BLACK_KNIGHT = 8;
    public static final byte BLACK_BISHOP = 9;
    public static final byte BLACK_ROOK = 10;
    public static final byte BLACK_QUEEN = 11;
    public static final byte BLACK_KING = 12;

    public static final int[][] KNIGHT = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
    public static final int[][] BISHOP = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public static final int[][] ROOK = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static final int[][] QUEEN = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static final int[][] KING = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static final int[] VALUES = {0, 1000, 3000, 3000, 5000, 10000, 1000000, -1000, -3000, -3000, -5000, -10000, -1000000};

}
