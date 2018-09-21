package com.aryaha.tictactoe.commands;

import com.aryaha.core.game.commands.AbstractCommand;
import com.aryaha.tictactoe.messages.PlaceMessage;

public class PlaceCommand extends AbstractCommand {
    public static final String COMMAND_TYPE = "place";

    public PlaceCommand(PlaceMessage msg) {
        super(msg);
    }

    @Override
    public String getCommandType() {
        return COMMAND_TYPE;
    }
}
