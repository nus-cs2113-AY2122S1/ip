package commands;

import task.Task;

import static common.Messages.MESSAGE_TASK_NOT_FOUND;

/**
 * Marks a task as completed, given a task index.
 */
public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = "done: Marks the task corresponding to the task number as complete.\n"
            + "\tParameters: TASK_INDEX\n"
            + "\tExample: done 2\n";
    public static final String MESSAGE_SUCCESS = "Completed: %1$s\n" +
            "Wow. You finally completed a task after lazying around all day.";

    public MarkAsDoneCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            Task toMarkDone = getTargetTask();
            int targetIndex = getTargetIndex();
            taskManager.markTaskAsDone(targetIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toMarkDone.toString()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(MESSAGE_TASK_NOT_FOUND, taskManager.getTaskList().size()));
        }
    }
}
