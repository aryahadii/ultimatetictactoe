package src.tictactoe.commands;

import org.json.simple.JSONObject;
import src.core.game.commands.AbstractCommand;
import src.tictactoe.messages.PlaceMessage;

public class PlaceCommand extends AbstractCommand {

    public PlaceCommand(PlaceMessage msg) {
        super(msg);
    }

    public JSONObject toJson() {
        JSONObject representation = new JSONObject();
        return representation;
    }

    public String toJsonString() {
        return toJson().toString();
    }

    @Override
    public String getCommandType() {
        return "place";
    }

}
