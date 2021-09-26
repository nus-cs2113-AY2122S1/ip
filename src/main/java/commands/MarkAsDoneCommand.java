package commands;

import task.Task;

public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = "done: Marks the task corresponding to the task number as complete.\n"
            + "Parameters: TASK_INDEX\n"
            + "Example: done 2";
    public static final String MESSAGE_SUCCESS = "Completed: %1$s\n"+
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
            return new CommandResult("The task index should be between 1 and "
                    +taskManager.getTaskList().size() +"\nTry again.");
        }
    }
}
