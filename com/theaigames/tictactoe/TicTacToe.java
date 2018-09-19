package com.theaigames.tictactoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.theaigames.engine.io.IOPlayer;
import com.theaigames.game.AbstractGame;
import com.theaigames.tictactoe.field.Field;
import com.theaigames.tictactoe.player.Player;

public class TicTacToe extends AbstractGame {

    private final int TIMEBANK_MAX = 10000;
    private final int TIME_PER_MOVE = 500;
    private List<Player> players;

    public TicTacToe(List<String> botsExecPaths) throws IOException {
        for (String botPath : botsExecPaths) {
            String botId = UUID.randomUUID().toString();
            this.engine.addPlayer(botPath, botId);
        }
    }

    @Override
    public void setupGame(List<IOPlayer> ioPlayers) {
        // create all the players and everything they need
        this.players = new ArrayList<>();

        // create the playing field
        Field mField = new Field();

        for (int i = 0; i < ioPlayers.size(); i++) {
            // create the player
            String playerName = String.format("player%d", i + 1);
            Player player = new Player(playerName, ioPlayers.get(i), TIMEBANK_MAX, TIME_PER_MOVE, i + 1);
            this.players.add(player);

        }
        this.players.forEach(this::sendSettings);

        // create the processor
        super.processor = new Processor(this.players, mField);
    }

    private void sendSettings(Player player) {
        String playerString = this.players.get(0).getName() + "," + this.players.get(1).getName();

        player.sendSetting("timebank", TIMEBANK_MAX);
        player.sendSetting("time_per_move", TIME_PER_MOVE);
        player.sendSetting("player_names", playerString);
        player.sendSetting("your_bot", player.getName());
        player.sendSetting("your_botid", player.getId());
    }

    @Override
    protected void run() throws Exception {
        super.engine.setLogic(this);
        super.engine.start();
    }

}
