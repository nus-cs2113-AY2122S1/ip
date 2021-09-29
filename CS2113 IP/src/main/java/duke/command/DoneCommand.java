package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        int zeroIndex = taskIndex - 1;
        Task specifiedTask = tasks.get(zeroIndex);
        specifiedTask.markAsDone();
        ui.handleDone(specifiedTask);
    }
}
