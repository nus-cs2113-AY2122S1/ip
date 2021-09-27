package commands;

import constants.Message;

public class DoneCommand extends Command{

    private int index;
    private static final String TASK_COMPLETED_MESSAGE = "You've completed the task! Well done!";
    public static final String DONE_COMMAND = "done";

    public DoneCommand(int index) {
        this.index = index;
    }

    public CommandResult execute() throws IndexOutOfBoundsException {
        tasks.getTask(index).markAsDone();
        return new CommandResult(TASK_COMPLETED_MESSAGE,tasks.getTask(index),PrintOptions.ONLY_TASK);
    }
}
