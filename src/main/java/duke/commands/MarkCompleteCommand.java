package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Sets the specified task as done.
 */
public class MarkCompleteCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the selected task as Complete. "
            + "Parameters: TASK_INDEX"
            + "\n"
            + "Example: " + COMMAND_WORD
            + " 1\n";
    public static final String MESSAGE_FAILED_MARK_COMPLETE = "You've already done the task! Warning: Re-doing the same tasks may result in insanity.";

    private final String taskIndexString;

    /**
     * Simple constructor with raw values.
     *
     * @param taskIndexString a string that represents the index of the given task
     */
    public MarkCompleteCommand(String taskIndexString) {
        this.taskIndexString = taskIndexString;
    }

    /**
     * Finds the specified task with the index and marks it as done.
     * Overwrites the storage file with a new list that has the task marked done.
     * Shows the user a message for successful mark completion.
     * If the task is already done, shows the user a fail message.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     * @throws IndexOutOfBoundsException if the given index is out of the task list range
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws IndexOutOfBoundsException {
        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        Task currentTask = tasks.getTask(taskIndex);
        if (currentTask.isDone()) {
            ui.showToUser(MESSAGE_FAILED_MARK_COMPLETE);
        } else {
            currentTask.setDone();
            storage.OverwriteListToFile();
            ui.showSuccessfulComplete(currentTask);
        }
    }
}