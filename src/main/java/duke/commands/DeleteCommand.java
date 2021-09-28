package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from the task list. "
            + "Parameters: TASK_INDEX"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " 2\n||";

    public final String taskIndexString;

    public DeleteCommand(String taskIndexString) {
        this.taskIndexString = taskIndexString;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws IndexOutOfBoundsException {
        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        Task currentTask = tasks.getTask(taskIndex);
        tasks.removeTask(currentTask);
        storage.OverwriteListToFile();
        ui.showSuccessfulDelete(currentTask, tasks);
    }
}