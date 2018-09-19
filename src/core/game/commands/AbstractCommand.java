package src.core.game.commands;

import src.tictactoe.messages.IMessage;

public abstract class AbstractCommand implements ICommand {
    protected IMessage msg;

    public AbstractCommand(IMessage msg) {
        this.msg = msg;
    }

    public IMessage getMessage() {
        return msg;
    }
}
