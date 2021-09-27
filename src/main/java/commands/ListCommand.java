package commands;

import task.Task;

/**
 * A Command class that contains methods that asks the Ui to print out the ArrayList.
 */
public class ListCommand extends Command{

    public static final String LIST_IS_EMPTY = "You're a free man :)";
    private static final String LIST_IS_NOT_EMPTY = "Here are the things you need to do :";

    /**
     * Asks the Ui to print the list if it's not empty and inform the user if it's empty
     * @return A CommandResult that is passed to the Ui to show the user the result.
     */
    @Override
    public CommandResult execute(){
        if(Task.getTotalTasks() == 0) {
            return new CommandResult(LIST_IS_EMPTY,PrintOptions.DEFAULT);
        }
        else {
            return new CommandResult(LIST_IS_NOT_EMPTY,tasks,PrintOptions.LIST);
        }
    }
}
