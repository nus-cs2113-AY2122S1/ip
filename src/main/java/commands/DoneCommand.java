package commands;

import constants.Message;

public class DoneCommand extends Command{

    private String input;
    private static final int EXPECTED_LENGTH_FOR_DONE_INPUT = 2;
    private static final String TASK_COMPLETED_MESSAGE = "You've completed the task! Well done!";
    public static final String DONE_COMMAND = "done";

    public DoneCommand(String input) {
        this.input = input;
    }

    public CommandResult execute() throws IndexOutOfBoundsException,NumberFormatException {
        if(input.split(" ").length < EXPECTED_LENGTH_FOR_DONE_INPUT) {
            return new CommandResult(Message.PROMPT_TASK_NUMBER,PrintOptions.DEFAULT);
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.getTask(index).markAsDone();
        return new CommandResult(TASK_COMPLETED_MESSAGE,tasks.getTask(index),PrintOptions.ONLY_TASK);
    }
}
