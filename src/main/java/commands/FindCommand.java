package commands;

import task.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Finds all relevant tasks that contain a particular substring in the task description.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "find: Finds a task by searching for a keyword. \n"
            + "\tParameters: KEYWORD\n"
            + "\tExample: find books\n";
    public static final String MESSAGE_SUCCESS = "Here are the matching tasks: ";
    public static final String MESSAGE_NO_MATCHING_TASKS = "There are no tasks that match your keyword.";

    private final String filterString;

    /**
     * Constructor that sets the filter string.
     * @param filterString the substring that the user wants to find within the task description.
     */
    public FindCommand(String filterString) {
        this.filterString = filterString;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = taskManager.getTaskList();
        ArrayList<Task> filteredTaskList = filterTasksByString(allTasks, filterString);
        String feedbackToUser = MESSAGE_SUCCESS;
        if (filteredTaskList.isEmpty()) {
            feedbackToUser = MESSAGE_NO_MATCHING_TASKS;
        }
        return new CommandResult(feedbackToUser, filteredTaskList);
    }

    private static ArrayList<Task> filterTasksByString(ArrayList<Task> taskList, String filterString) {
        return (ArrayList<Task>) taskList.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
    }
}
