package commands;

import common.Messages;
import task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "\nlist: Displays all tasks in the task list in a sorted order.\n"
            + "\tEvents and Deadlines are sorted in ascending order by date. Todos are at the bottom of the list.\n"
            + "\tExample: list\n";
    public static final String MESSAGE_LIST_ALL_TASKS = "\nLook at that ever-expanding to-do list.\n"
            +"You really should stop procrastinating.\n";
    public static final String MESSAGE_EMPTY_TASK_LIST = "There are no tasks in your to-do list. Bet that'll change soon.";


    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = taskManager.getSortedList();
        String feedbackToUser = MESSAGE_LIST_ALL_TASKS;
        if (allTasks.isEmpty()) {
            feedbackToUser = MESSAGE_EMPTY_TASK_LIST;
        }
        return new CommandResult(feedbackToUser, allTasks);
    }

}
