package duke;

import duke.exceptions.DukeInvalidTaskIndex;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public class DeleteTaskCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    private int indexOfTask;
    
    public DeleteTaskCommand(int indexOfTask) {
        this.indexOfTask = indexOfTask;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeInvalidTaskIndex,
            IOException {
        Task removedTask = tasks.removeTask(indexOfTask);
        ui.acknowledgeRemoveCommand(removedTask, tasks.getNumberOfTasks());
    }
}
