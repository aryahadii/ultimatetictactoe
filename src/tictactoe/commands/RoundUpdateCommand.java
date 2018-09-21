package src.tictactoe.commands;

import src.core.game.commands.AbstractCommand;
import src.tictactoe.messages.RoundUpdateMessage;

public class RoundUpdateCommand extends AbstractCommand {
    public static final String COMMAND_TYPE = "round_update";

    public RoundUpdateCommand(RoundUpdateMessage msg) {
        super(msg);

        representation.put("command", getCommandType());
        representation.put("field", msg.getFieldRepresentation());
        representation.put("round_number", msg.getRoundNumber());
        representation.put("move_number", msg.getMoveNumber());
    }

    @Override
    public String getCommandType() {
        return COMMAND_TYPE;
    }
}
