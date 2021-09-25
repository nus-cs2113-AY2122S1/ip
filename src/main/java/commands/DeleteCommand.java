package commands;

import duke.DukeException;
import task.Task;

import static commands.ListCommand.LIST_IS_EMPTY;

public class DeleteCommand extends Command{
    private static final int EXPECTED_LENGTH_FOR_DELETE_INPUT = 6;
    public static final String TASK_REMOVED = "Alright, I've removed this task:";
    public static final String PROMPT_SENSIBLE_INDEX = "Please give a number between 1 and ";
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    public CommandResult execute() throws DukeException,
            NumberFormatException {
        if(input.split(" ")[0].length() < EXPECTED_LENGTH_FOR_DELETE_INPUT) {
            throw new DukeException();
        }
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        try {
            Task temp = tasks.getTask(index);
            tasks.deleteTask(index);
            return new CommandResult(TASK_REMOVED,temp,"delete");
        } catch (IndexOutOfBoundsException error){
            return new CommandResult(getSensibleRange(Task.getTotalTasks()));
        }
    }

    public static String getSensibleRange(int number) {
        if(number < 1) {
            return LIST_IS_EMPTY;
        }
        return PROMPT_SENSIBLE_INDEX + number + " :)";
    }
}
