package com.aryaha.tictactoe.board;

class LocalBoard {
    static final int EMPTY_CELL = 0;
    private static final int colsCount = 3, rowsCount = 3;
    private int[][] cells;

    LocalBoard() {
        cells = new int[rowsCount][colsCount];
        clearBoard();
    }

    private void clearBoard() {
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                cells[y][x] = EMPTY_CELL;
            }
        }
    }

    void placeMark(Location localBoardLoc, int playerId) throws Exception {
        if (!isMarkValid(localBoardLoc)) {
            throw new Exception("place is not valid");
        }

        int boardX = localBoardLoc.getX();
        int boardY = localBoardLoc.getY();
        cells[boardY][boardX] = playerId;
    }

    private boolean isMarkValid(Location localBoardLoc) {
        if (localBoardLoc.getX() >= colsCount || localBoardLoc.getX() < 0) {
            return false;
        }
        if (localBoardLoc.getY() >= rowsCount || localBoardLoc.getY() < 0) {
            return false;
        }
        if (cells[localBoardLoc.getY()][localBoardLoc.getX()] != EMPTY_CELL) {
            return false;
        }
        return true;
    }

    boolean hasPossibleMove() {
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                if (cells[y][x] == EMPTY_CELL) {
                    return true;
                }
            }
        }
        return false;
    }

    int getWinnerPlayerId() {
        return Utils.getWinnerPlayerId(cells, EMPTY_CELL);
    }

    public int getMark(Location loc) {
        return cells[loc.getY()][loc.getX()];
    }

    @Override
    public String toString() {
        StringBuilder representer = new StringBuilder();
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                representer.append(cells[y][x]);
            }
        }
        return representer.toString();
    }
}
