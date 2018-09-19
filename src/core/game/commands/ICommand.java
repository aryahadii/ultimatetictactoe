package src.core.game.commands;

import src.tictactoe.messages.IMessage;

public interface ICommand {
    String toJsonString();
    IMessage getMessage();
    String getCommandType();
}
