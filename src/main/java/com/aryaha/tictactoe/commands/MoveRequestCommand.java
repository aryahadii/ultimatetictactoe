package com.aryaha.tictactoe.commands;

import com.aryaha.core.game.commands.AbstractCommand;
import com.aryaha.tictactoe.messages.MoveRequestMessage;

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
