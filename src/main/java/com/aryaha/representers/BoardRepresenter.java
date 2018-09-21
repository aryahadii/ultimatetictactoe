package com.aryaha.representers;

import com.aryaha.tictactoe.board.Board;
import com.aryaha.tictactoe.board.Location;

public class BoardRepresenter {
    public static final int colsCount = 9, rowsCount = 9;
    private static final int EMPTY_CELL = Board.EMPTY_CELL;
    private int[][] board;

    public BoardRepresenter() {
        board = new int[colsCount][rowsCount];
    }

    public int getMark(Location loc) {
        return board[loc.getY()][loc.getX()];
    }

    public static BoardRepresenter fromBoard(Board board) {
        BoardRepresenter newRepresenter = new BoardRepresenter();
        for (int y = 0; y < rowsCount; y++) {
            for (int x = 0; x < colsCount; x++) {
                Location loc = new Location(x, y);
                newRepresenter.board[y][x] = board.getMark(loc);
            }
        }
        return newRepresenter;
    }
}
