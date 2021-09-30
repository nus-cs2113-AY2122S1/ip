package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Deletes a specific task from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from the task list. "
            + "Parameters: TASK_INDEX"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " 2\n||";

    public final String taskIndexString;

    /**
     * Simple constructor using raw values
     *
     * @param taskIndexString a string which represents the index of the given task.
     */
    public DeleteCommand(String taskIndexString) {
        this.taskIndexString = taskIndexString;
    }

    /**
     * Finds the task from the given index and deletes it from the TaskList and storage file.
     * Shows the user a message for successful deletion.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     * @throws IndexOutOfBoundsException if the index is out of the task list range
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws IndexOutOfBoundsException {
        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        Task currentTask = tasks.getTask(taskIndex);
        tasks.removeTask(currentTask);
        storage.OverwriteListToFile();
        ui.showSuccessfulDelete(currentTask, tasks);
    }
}