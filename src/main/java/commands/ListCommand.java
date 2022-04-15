package commands;

import task.Task;

/**
 * A Command class that contains methods that asks the Ui to print out the ArrayList.
 */
public class ListCommand extends Command{

    public static final String LIST_IS_EMPTY = "You're a free man :)";
    private static final String LIST_IS_NOT_EMPTY = "Here are the things you need to do :";
    public static final String LIST_COMMAND = "list";
    public static final int NO_TASKS = 0;

    public ListCommand () {
        super();
    }

    /**
     * Sends the ArrayList to be printed to the Ui
     *
     * @return A CommandResult that tells the Ui to print the status of execution
     * and the list (if not empty).
     */
    @Override
    public CommandResult execute(){
        if(Task.getTotalTasks() == NO_TASKS) {
            return new CommandResult(LIST_IS_EMPTY,PrintOptions.DEFAULT);
        }
        return new CommandResult(LIST_IS_NOT_EMPTY,tasks,PrintOptions.LIST);
    }
}
