package src.representers;

import src.tictactoe.field.Field;

public class FieldRepresenter {
    private Mark[][] board;
    private int colsCount = 9, rowsCount = 9;

    public FieldRepresenter() {
        board = new Mark[colsCount][rowsCount];
    }

    public Mark getSpaceMark(Space space) {
        return board[space.y][space.x];
    }

    public static FieldRepresenter fromField(Field field) {
        FieldRepresenter newRepresenter = new FieldRepresenter();

        int[][] board = field.getBoard();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] > 0) {
                    newRepresenter.board[y][x] = new Mark(board[y][x]);
                } else {
                    newRepresenter.board[y][x] = new Mark();
                }
            }
        }

        return newRepresenter;
    }
}
