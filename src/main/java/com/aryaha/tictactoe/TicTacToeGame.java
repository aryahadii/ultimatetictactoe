package com.aryaha.tictactoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.aryaha.core.game.AbstractGame;
import com.aryaha.representers.BoardRepresenter;
import com.aryaha.tictactoe.commands.ServerSettingsCommand;
import com.aryaha.tictactoe.board.Board;
import com.aryaha.tictactoe.messages.SettingsMessage;
import com.aryaha.tictactoe.logic.TicTacToeLogic;
import com.aryaha.tictactoe.player.Player;

public class TicTacToeGame extends AbstractGame {
    private static TicTacToeGame instance = null;

    private final int TIME_BANK_MAX_MS = 10000;
    private final int MOVE_TIMEOUT_MS = 500;
    private List<Player> players;


    public static TicTacToeGame getInstance() {
        if (instance == null) {
            instance = new TicTacToeGame();
        }
        return instance;
    }

    private TicTacToeGame() {
        super();
    }

    public void addBots(List<String> botsExecPaths) throws IOException {
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
        this.players = new ArrayList<>();
        for (int i = 0; i < engine.getPlayers().size(); i++) {
            String playerName = String.format("player%d", i + 1);
            Player player = new Player(playerName, engine.getPlayers().get(i), TIME_BANK_MAX_MS, MOVE_TIMEOUT_MS, i + 1);
            this.players.add(player);

        }
        this.players.forEach(this::sendGameSettings);

        super.logic = new TicTacToeLogic(this.players, new Board());
    }

    private SettingsMessage createGameSettings(Player player) {
        SettingsMessage gameSettingsMessage = new SettingsMessage();
        for (Player p : this.players) {
            gameSettingsMessage.addPlayerName(p.getName());
        }
        gameSettingsMessage.setBotId(player.getId());
        gameSettingsMessage.setMoveTimeoutInMs(MOVE_TIMEOUT_MS);
        gameSettingsMessage.setTimeLimitInMs(TIME_BANK_MAX_MS);
        return gameSettingsMessage;
    }

    private void sendGameSettings(Player player) {
        SettingsMessage settingsMessage = createGameSettings(player);
        player.sendCommand(new ServerSettingsCommand(settingsMessage));
    }

    public BoardRepresenter getBoardData() {
        return BoardRepresenter.fromBoard(((TicTacToeLogic) this.logic).getBoard());
    }
}
