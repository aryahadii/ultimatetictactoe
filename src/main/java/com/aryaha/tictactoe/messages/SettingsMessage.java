package com.aryaha.tictactoe.messages;

import java.util.ArrayList;
import java.util.List;

public class SettingsMessage extends AbstractMessage {
    private List<String> playersName;
    private int botId;
    private int msMoveTimeout, msTimeLimit;

    public SettingsMessage() {
        playersName = new ArrayList<>();
    }

    public void addPlayerName(String name) {
        playersName.add(name);
    }

    public List<String> getPlayersName() {
        return playersName;
    }

    public void setMoveTimeoutInMs(int timeout) {
        msMoveTimeout = timeout;
    }

    public int getMoveTimeoutInMs() {
        return msMoveTimeout;
    }

    public void setTimeLimitInMs(int limit) {
        msTimeLimit = limit;
    }

    public int getTimeLimitInMs() {
        return msTimeLimit;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public int getBotId() {
        return botId;
    }
}
