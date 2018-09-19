package src.tictactoe.messages;

import src.tictactoe.field.Field;

public class RoundUpdateMessage extends AbstractMessage {
    private int roundNumber, moveNumber;
    private Field field;

    public void setField(Field field) {
        this.field = field;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public String getFieldRepresentation() {
        return field.toString();
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
