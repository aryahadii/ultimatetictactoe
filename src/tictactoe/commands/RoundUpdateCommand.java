package src.tictactoe.commands;

import org.json.simple.JSONObject;
import src.core.game.commands.AbstractCommand;
import src.tictactoe.messages.RoundUpdateMessage;

public class RoundUpdateCommand extends AbstractCommand {

    public RoundUpdateCommand(RoundUpdateMessage msg) {
        super(msg);
    }

    public JSONObject toJson() {
        JSONObject representation = new JSONObject();
        representation.put("command", getCommandType());
        representation.put("field", ((RoundUpdateMessage) msg).getFieldRepresentation());
        representation.put("round_number", ((RoundUpdateMessage) msg).getRoundNumber());
        representation.put("move_number", ((RoundUpdateMessage) msg).getMoveNumber());
        return representation;
    }

    public String toJsonString() {
        return toJson().toString();
    }

    @Override
    public String getCommandType() {
        return "round_update";
    }

}
