package com.aryaha.tictactoe.messages;

import com.aryaha.tictactoe.board.Location;

public class PlaceMessage extends AbstractMessage {
    private int x, y;

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

    public Location toLocation() {
        return new Location(x, y);
    }
}
