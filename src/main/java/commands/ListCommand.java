package commands;

import task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "list: Displays all tasks in the task list in a sorted order."
            + "Events and Deadlines are sorted in ascending order by date. Todos are at the bottom of the list.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = taskManager.getSortedList();
        return new CommandResult(getMessageForTaskListDisplayed(allTasks), allTasks);
    }

}
