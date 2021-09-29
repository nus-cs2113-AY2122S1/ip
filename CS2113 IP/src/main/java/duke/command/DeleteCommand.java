package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        tasks.deleteTask(taskIndex, ui);
    }
}
