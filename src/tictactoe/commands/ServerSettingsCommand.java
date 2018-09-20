package src.tictactoe.commands;

import org.json.simple.JSONObject;
import src.core.game.commands.AbstractCommand;
import src.tictactoe.messages.SettingsMessage;

public class ServerSettingsCommand extends AbstractCommand {

    public ServerSettingsCommand(SettingsMessage msg) {
        super(msg);
    }

    public JSONObject toJson() {
        JSONObject representation = new JSONObject();
        representation.put("command", getCommandType());
        representation.put("players", ((SettingsMessage) msg).getPlayersName());
        representation.put("bot_id", ((SettingsMessage) msg).getBotId());
        representation.put("move_timeout", ((SettingsMessage) msg).getMoveTimeoutInMs());
        representation.put("time_limit", ((SettingsMessage) msg).getTimeLimitInMs());
        return representation;
    }

    public String toJsonString() {
        return toJson().toString();
    }

    @Override
    public String getCommandType() {
        return "settings";
    }
}
