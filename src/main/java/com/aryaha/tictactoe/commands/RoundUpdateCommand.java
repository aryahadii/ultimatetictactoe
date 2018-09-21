package com.aryaha.tictactoe.commands;

import com.aryaha.core.game.commands.AbstractCommand;
import com.aryaha.tictactoe.messages.RoundUpdateMessage;

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
