package com.theaigames.tictactoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.theaigames.core.game.AbstractGame;
import com.theaigames.tictactoe.field.Field;
import com.theaigames.tictactoe.logic.TicTacToeLogic;
import com.theaigames.tictactoe.player.Player;

public class TicTacToeGame extends AbstractGame {

    private final int TIME_BANK_MAX_MS = 10000;
    private final int MOVE_TIMEOUT_MS = 500;
    private List<Player> players;
    private Field gameField;

    public TicTacToeGame(List<String> botsExecPaths) throws IOException {
        super();

        this.addPlayersToEngine(botsExecPaths);
        this.setupGame();
    }

    private void addPlayersToEngine(List<String> botsExecPaths) throws IOException {
        for (String botPath : botsExecPaths) {
            String botId = UUID.randomUUID().toString();
            this.engine.addPlayer(botPath, botId);
        }
    }

    private void setupGame() {
        this.gameField = new Field();

        this.players = new ArrayList<>();
        for (int i = 0; i < engine.getPlayers().size(); i++) {
            String playerName = String.format("player%d", i + 1);
            Player player = new Player(playerName, engine.getPlayers().get(i), TIME_BANK_MAX_MS, MOVE_TIMEOUT_MS, i + 1);
            this.players.add(player);

        }
        this.players.forEach(this::sendGameSettings);

        super.logic = new TicTacToeLogic(this.players, this.gameField);
    }

    private void sendGameSettings(Player player) {
        String playersName = String.format("%s, %s", players.get(0).getName(), players.get(1).getName());

        player.sendSetting("players_name", playersName);
        player.sendSetting("bot_name", player.getName());
        player.sendSetting("bot_id", player.getId());
        player.sendSetting("move_timeout", MOVE_TIMEOUT_MS);
        player.sendSetting("time_bank", TIME_BANK_MAX_MS);
    }

}
