package src.core.game.commands;

import org.json.simple.JSONObject;
import src.tictactoe.messages.IMessage;

public abstract class AbstractCommand implements ICommand {
    protected IMessage msg;
    protected  JSONObject representation = new JSONObject();

    public AbstractCommand(IMessage msg) {
        this.msg = msg;
    }

    public JSONObject toJson() {
        return representation;
    }

    public String toJsonString() {
        return toJson().toString();
    }

    public IMessage getMessage() {
        return msg;
    }
}
