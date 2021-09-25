package commands;

import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "\nfind: Finds a task by searching for a keyword. \n"
            + "\tFormat: find {KEYWORD}\n"
            + "\tExample: find books\n";
    private static final String MESSAGE_SUCCESS = "Here are the matching tasks: ";
    private static final String MESSAGE_NO_MATCHING_TASKS = "There are no tasks that match your keyword.";

    private final String filterString;

    public FindCommand(String filterString) {
        this.filterString = filterString;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = taskManager.getTaskList();
        ArrayList<Task> filteredTaskList = Ui.filterTasksByString(allTasks, filterString);
        String feedbackToUser = MESSAGE_SUCCESS;
        if (filteredTaskList.isEmpty()) {
            feedbackToUser = MESSAGE_NO_MATCHING_TASKS;
        }
        return new CommandResult(feedbackToUser, filteredTaskList);
    }
}
