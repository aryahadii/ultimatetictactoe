package src.representers;

public class Mark {
    private boolean isEmpty;
    private int playerId;

    public Mark() {
        isEmpty = true;
    }

    public Mark(int playerId) {
        isEmpty = false;
        this.playerId = playerId;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getPlayerId() throws Exception {
        if (isEmpty()) {
            throw new Exception("it's empty");
        }
        return playerId;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "-";
        }
        return String.valueOf(playerId);
    }
}
