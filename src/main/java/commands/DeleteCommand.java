package commands;

import constants.Message;
import task.Task;

import static commands.ListCommand.LIST_IS_EMPTY;

/**
 * A Command class that deletes a task in the ArrayList.
 */
public class DeleteCommand extends Command{
    private static final int EXPECTED_LENGTH_FOR_DELETE_INPUT = 2;
    public static final String TASK_REMOVED = "Alright, I've removed this task:";
    public static final String PROMPT_SENSIBLE_INDEX = "Please give a number between 1 and ";
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes a task with a given index on the ArrayList
     * @return A CommandResult that tells the Ui to print the status of execution
     * and the deleted task.
     * @throws NumberFormatException Throws an exception if the input given is not a number.
     */
    @Override
    public CommandResult execute() throws NumberFormatException {
        if(input.split(" ").length < EXPECTED_LENGTH_FOR_DELETE_INPUT) {
            return new CommandResult(Message.PROMPT_TASK_NUMBER,PrintOptions.DEFAULT);
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        try {
            Task temp = tasks.getTask(index);
            tasks.deleteTask(index);
            return new CommandResult(TASK_REMOVED,temp,PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        } catch (IndexOutOfBoundsException error){
            return new CommandResult(Message.giveSensibleRange(Task.getTotalTasks()),PrintOptions.DEFAULT);
        }
    }
}
