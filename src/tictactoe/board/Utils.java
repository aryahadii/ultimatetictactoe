package src.tictactoe.board;

class Utils {
    static int getWinnerPlayerId(int[][] spaces, int emptyValue) {
        int horizontalWinner = getHorizontalWinnerPlayerId(spaces, emptyValue);
        if (horizontalWinner != emptyValue)
            return horizontalWinner;

        int verticalWinner = Utils.getVerticalWinnerPlayerId(spaces, emptyValue);
        if (verticalWinner != emptyValue)
            return verticalWinner;

        int diagonalWinner = Utils.getDiagonalWinnerPlayerId(spaces, emptyValue);
        if (diagonalWinner != emptyValue)
            return diagonalWinner;

        return emptyValue;
    }

    private static int getHorizontalWinnerPlayerId(int[][] spaces, int emptyValue) {
        for (int y = 0; y < spaces.length; y++) {
            if (spaces[y][0] != emptyValue && equal(spaces[y][0], spaces[y][1], spaces[y][2])) {
                return spaces[y][0];
            }
        }
        return emptyValue;
    }

    private static int getVerticalWinnerPlayerId(int[][] spaces, int emptyValue) {
        for (int x = 0; x < spaces[0].length; x++) {
            if (spaces[0][x] != emptyValue && equal(spaces[0][x], spaces[1][x], spaces[2][x])) {
                return spaces[0][x];
            }
        }
        return emptyValue;
    }

    private static int getDiagonalWinnerPlayerId(int[][] spaces, int emptyValue) {
        if (spaces[0][0] != emptyValue && equal(spaces[0][0], spaces[1][1], spaces[2][2])) {
            return spaces[0][0];
        }
        if (spaces[2][0] != emptyValue && equal(spaces[2][0], spaces[1][1], spaces[0][2])) {
            return spaces[0][0];
        }
        return emptyValue;
    }

    private static boolean equal(Object a, Object b, Object c) {
        if (a.equals(b) && b.equals(c)) {
            return true;
        }
        return false;
    }
}
