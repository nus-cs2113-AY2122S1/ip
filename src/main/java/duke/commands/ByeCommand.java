package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private static final String MESSAGE_FAREWELL = "Bye. Hope to see you again soon!";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return MESSAGE_FAREWELL;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
