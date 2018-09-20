package src.tictactoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import src.core.game.AbstractGame;
import src.representers.FieldRepresenter;
import src.tictactoe.commands.ServerSettingsCommand;
import src.tictactoe.field.Field;
import src.tictactoe.messages.SettingsMessage;
import src.tictactoe.logic.TicTacToeLogic;
import src.tictactoe.player.Player;

public class TicTacToeGame extends AbstractGame {
    private static TicTacToeGame instance = null;

    private final int TIME_BANK_MAX_MS = 10000;
    private final int MOVE_TIMEOUT_MS = 500;
    private List<Player> players;
    private Field gameField;


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

    private SettingsMessage createGameSettings() {
        SettingsMessage gameSettingsMessage = new SettingsMessage();
        for (Player player : this.players) {
            gameSettingsMessage.addPlayerName(player.getName());
        }
        gameSettingsMessage.setMoveTimeoutInMs(MOVE_TIMEOUT_MS);
        gameSettingsMessage.setTimelimitInMs(TIME_BANK_MAX_MS);
        return gameSettingsMessage;
    }

    private void sendGameSettings(Player player) {
        SettingsMessage settingsMessage = createGameSettings();
        player.sendCommand(new ServerSettingsCommand(settingsMessage));
    }

    public FieldRepresenter getFieldData() {
        return FieldRepresenter.fromField(((TicTacToeLogic) this.logic).getField());
    }
}
