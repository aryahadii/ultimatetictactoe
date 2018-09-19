package src.tictactoe.messages;

public class PlaceMessage extends AbstractMessage {
    int x, y;

    public PlaceMessage(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
