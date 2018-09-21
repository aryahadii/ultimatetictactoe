package com.aryaha.core.game.commands;

import com.aryaha.tictactoe.messages.IMessage;

public interface ICommand {
    String toJsonString();
    IMessage getMessage();
    String getCommandType();
}
