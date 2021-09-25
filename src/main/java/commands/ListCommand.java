package commands;

import task.Task;

public class ListCommand extends Command{

    public static final String LIST_IS_EMPTY = "You're a free man :)";
    private static final String LIST_IS_NOT_EMPTY = "Here are the things you need to do :";

    public CommandResult execute(){
        if(Task.getTotalTasks() == 0) {
            return new CommandResult(LIST_IS_EMPTY,PrintOptions.DEFAULT);
        }
        else {
            return new CommandResult(LIST_IS_NOT_EMPTY,tasks,PrintOptions.LIST);
        }
    }
}
