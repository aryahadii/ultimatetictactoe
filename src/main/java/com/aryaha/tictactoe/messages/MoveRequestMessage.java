package com.aryaha.tictactoe.messages;

public class MoveRequestMessage extends AbstractMessage {
    long timeLimit;

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public long getTimeLimit() {
        return timeLimit;
    }
}
