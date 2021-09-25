package commands;

import duke.DukeException;

public class DoneCommand extends Command{
    private String input;
    private static final int EXPECTED_LENGTH_FOR_DONE_INPUT = 6;
    private static final String TASK_COMPLETED_MESSAGE = "You've completed the task! Well done!";

    public DoneCommand(String input) {
        this.input = input;
    }

    public CommandResult execute() throws DukeException,
            IndexOutOfBoundsException,NumberFormatException {
        if(input.length() < EXPECTED_LENGTH_FOR_DONE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.getTask(index).markAsDone();
        return new CommandResult(TASK_COMPLETED_MESSAGE,tasks.getTask(index),PrintOptions.ONLY_TASK);
    }
}
