package commands;

import constants.Message;

/**
 * A Command class that marks a task in the ArrayList as done
 */
public class DoneCommand extends Command{
    private String input;
    private static final int EXPECTED_LENGTH_FOR_DONE_INPUT = 2;
    private static final String TASK_COMPLETED_MESSAGE = "You've completed the task! Well done!";

    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Marks a task of a given index in the ArrayList as done
     * @return A CommandResult that that tells the Ui to print the status of execution
     * and the task that is done.
     * @throws NumberFormatException Throws an exception if the input given is not a number.
     */
    @Override
    public CommandResult execute() throws NumberFormatException {
        if(input.split(" ").length < EXPECTED_LENGTH_FOR_DONE_INPUT) {
            return new CommandResult(Message.PROMPT_TASK_NUMBER,PrintOptions.DEFAULT);
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.getTask(index).markAsDone();
        return new CommandResult(TASK_COMPLETED_MESSAGE,tasks.getTask(index),PrintOptions.ONLY_TASK);
    }
}
