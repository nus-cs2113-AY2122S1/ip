package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddEventTask extends Command {

    private String userCommand;

    public AddEventTask(String userCommand) {
        this.userCommand = userCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(userCommand);
        storage.saveData(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
