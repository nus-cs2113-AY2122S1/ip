package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

public class MarkCompleteCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the selected task as Complete. "
            + "Parameters: TASK_INDEX"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " 1\n||";
    public static final String MESSAGE_FAILED_MARK_COMPLETE = "You've already done the task! Warning: Re-doing the same tasks may result in insanity.";

    private final String taskIndexString;

    public MarkCompleteCommand(String taskIndexString) {
        this.taskIndexString = taskIndexString;
    }

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