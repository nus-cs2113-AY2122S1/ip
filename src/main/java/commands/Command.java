package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;

public abstract class Command {

    public void execute(TextUI ui, TaskList tasks, Storage data) {
        throw new UnsupportedOperationException();
    }

    public Boolean isStop() {
        return this instanceof ByeCommand;
    }
}
