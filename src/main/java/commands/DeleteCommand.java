package commands;

import task.Task;

/**
 * A Command class that deletes a task in the ArrayList.
 */
public class DeleteCommand extends Command{

    public static final String DELETE_COMMAND = "delete";
    public static final String TASK_REMOVED = "Alright, I've removed this task:";
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task with a given index on the ArrayList
     *
     * @return A CommandResult that tells the Ui to print the status of execution
     * and the deleted task.
     * @throws NumberFormatException Throws an exception if the input given is not a number.
     */
    @Override
    public CommandResult execute() throws IndexOutOfBoundsException {
        Task temp = tasks.getTask(index);
        tasks.deleteTask(index);
        return new CommandResult(TASK_REMOVED,temp,PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
    }
}
