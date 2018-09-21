package src.tictactoe.commands;

import src.core.game.commands.AbstractCommand;
import src.tictactoe.messages.MoveRequestMessage;

public class MoveRequestCommand extends AbstractCommand {
    public static final String COMMAND_TYPE = "move";

    public MoveRequestCommand(MoveRequestMessage msg) {
        super(msg);

        representation.put("command", getCommandType());
        representation.put("time_limit", msg.getTimeLimit());
    }

    @Override
    public String getCommandType() {
        return COMMAND_TYPE;
    }
}
