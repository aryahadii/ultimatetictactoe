package src.tictactoe.field;

class LocalBoard {
    static final int EMPTY_SPACE = 0;

    private static final int colsCount = 3, rowsCount = 3;
    private int[][] spaces;

    LocalBoard() {
        spaces = new int[rowsCount][colsCount];
        clearBoard();
    }

    private void clearBoard() {
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                spaces[y][x] = EMPTY_SPACE;
            }
        }
    }

    void placeMark(Location localBoardLoc, int playerId) throws Exception {
        if (!isMarkValid(localBoardLoc)) {
            throw new Exception("place is not valid");
        }

        int boardX = localBoardLoc.getX();
        int boardY = localBoardLoc.getY();
        spaces[boardY][boardX] = playerId;
    }

    private boolean isMarkValid(Location localBoardLoc) {
        if (localBoardLoc.getX() >= colsCount || localBoardLoc.getX() < 0) {
            return false;
        }
        if (localBoardLoc.getY() >= rowsCount || localBoardLoc.getY() < 0) {
            return false;
        }
        if (spaces[localBoardLoc.getY()][localBoardLoc.getX()] != EMPTY_SPACE) {
            return false;
        }
        return true;
    }

    boolean hasPossibleMove() {
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                if (spaces[y][x] == EMPTY_SPACE) {
                    return true;
                }
            }
        }
        return false;
    }

    int getWinnerPlayerId() {
        return Utils.getWinnerPlayerId(spaces, EMPTY_SPACE);
    }

    @Override
    public String toString() {
        StringBuilder representer = new StringBuilder();
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                representer.append(spaces[y][x]);
            }
        }
        return representer.toString();
    }
}
