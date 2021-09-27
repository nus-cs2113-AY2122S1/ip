package commands;

/**
 * A Command class that marks a task in the ArrayList as done
 */
public class DoneCommand extends Command{

    private final int index;
    private static final String TASK_COMPLETED_MESSAGE = "You've completed the task! Well done!";
    public static final String DONE_COMMAND = "done";

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task of a given index in the ArrayList as done
     *
     * @return A CommandResult that that tells the Ui to print the status of execution
     * and the task that is done.
     * @throws IndexOutOfBoundsException Throws an exception if the input given is not a number.
     */
    @Override

    public CommandResult execute() throws IndexOutOfBoundsException {
        tasks.getTask(index).markAsDone();
        return new CommandResult(TASK_COMPLETED_MESSAGE,tasks.getTask(index),PrintOptions.ONLY_TASK);
    }
}
