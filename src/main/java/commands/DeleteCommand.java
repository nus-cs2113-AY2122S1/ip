package commands;

import task.Task;

import static commands.ListCommand.LIST_IS_EMPTY;

public class DeleteCommand extends Command{

    public static final String DELETE_COMMAND = "delete";
    public static final String TASK_REMOVED = "Alright, I've removed this task:";;
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public CommandResult execute() throws IndexOutOfBoundsException {
        Task temp = tasks.getTask(index);
        tasks.deleteTask(index);
        return new CommandResult(TASK_REMOVED,temp,PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
    }
}
