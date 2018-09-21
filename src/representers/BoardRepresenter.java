package src.representers;

import src.tictactoe.field.Board;

public class BoardRepresenter {
    private Mark[][] board;
    private int colsCount = 9, rowsCount = 9;

    public BoardRepresenter() {
        board = new Mark[colsCount][rowsCount];
    }

    public Mark getSpaceMark(Space space) {
        return board[space.y][space.x];
    }

    public static BoardRepresenter fromBoard(Board board) {
        BoardRepresenter newRepresenter = new BoardRepresenter();

//        int[][] board = field.getBoard();
//        for (int y = 0; y < board.length; y++) {
//            for (int x = 0; x < board[y].length; x++) {
//                if (board[y][x] > 0) {
//                    newRepresenter.board[y][x] = new Mark(board[y][x]);
//                } else {
//                    newRepresenter.board[y][x] = new Mark();
//                }
//            }
//        }
        // TODO

        return newRepresenter;
    }
}
