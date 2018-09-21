package src.tictactoe.messages;

import src.tictactoe.board.Board;

public class RoundUpdateMessage extends AbstractMessage {
    private int roundNumber, moveNumber;
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public String getFieldRepresentation() {
        return board.toString();
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
