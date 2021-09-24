package commands;

import task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "list: Displays all tasks in the task list."
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = taskManager.getTaskList();
        return new CommandResult(getMessageForTaskListDisplayed(allTasks), allTasks);
    }

}
