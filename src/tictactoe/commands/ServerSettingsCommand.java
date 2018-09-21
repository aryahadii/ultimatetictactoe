package src.tictactoe.commands;

import src.core.game.commands.AbstractCommand;
import src.tictactoe.messages.SettingsMessage;

public class ServerSettingsCommand extends AbstractCommand {
    public static final String COMMAND_TYPE = "settings";

    public ServerSettingsCommand(SettingsMessage msg) {
        super(msg);

        representation.put("command", getCommandType());
        representation.put("players", msg.getPlayersName());
        representation.put("bot_id", msg.getBotId());
        representation.put("move_timeout", msg.getMoveTimeoutInMs());
        representation.put("time_limit", msg.getTimeLimitInMs());
    }

    @Override
    public String getCommandType() {
        return COMMAND_TYPE;
    }
}
