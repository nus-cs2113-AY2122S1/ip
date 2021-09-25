package commands;

import constants.Message;
import task.Task;

import static commands.ListCommand.LIST_IS_EMPTY;

public class DeleteCommand extends Command{
    private static final int EXPECTED_LENGTH_FOR_DELETE_INPUT = 2;
    public static final String TASK_REMOVED = "Alright, I've removed this task:";
    public static final String PROMPT_SENSIBLE_INDEX = "Please give a number between 1 and ";
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

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
            return new CommandResult(getSensibleRange(Task.getTotalTasks()),PrintOptions.DEFAULT);
        }
    }

    public static String getSensibleRange(int number) {
        if(number < 1) {
            return LIST_IS_EMPTY;
        }
        return PROMPT_SENSIBLE_INDEX + number + " :)";
    }
}
