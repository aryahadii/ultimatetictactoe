package src.tictactoe.commands;

import src.core.game.commands.AbstractCommand;
import src.tictactoe.messages.PlaceMessage;

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
