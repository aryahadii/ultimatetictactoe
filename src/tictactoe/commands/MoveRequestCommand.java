package src.tictactoe.commands;

import org.json.simple.JSONObject;
import src.core.game.commands.AbstractCommand;
import src.tictactoe.messages.MoveRequestMessage;

public class MoveRequestCommand extends AbstractCommand {

    public MoveRequestCommand(MoveRequestMessage msg) {
        super(msg);
    }

    public JSONObject toJson() {
        JSONObject representation = new JSONObject();
        representation.put("command", getCommandType());
        representation.put("time_limit", ((MoveRequestMessage) msg).getTimeLimit());
        return representation;
    }

    @Override
    public String toJsonString() {
        return toJson().toString();
    }

    @Override
    public String getCommandType() {
        return "move";
    }

}
