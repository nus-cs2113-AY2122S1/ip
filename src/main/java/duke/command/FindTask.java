package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindTask extends Command{

    private String userCommand;

    public FindTask(String userCommand) { this.userCommand = userCommand; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(userCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
