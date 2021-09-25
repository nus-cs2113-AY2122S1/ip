package commands;

import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds a task by searching for a keyword. \n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Here are the matching tasks: ";

    private final String filterString;

    public FindCommand(String filterString) {
        this.filterString = filterString;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Task> allTasks = taskManager.getTaskList();
        ArrayList<Task> filteredTaskList = Ui.filterTasksByString(allTasks, filterString);
        return new CommandResult(getMessageForTaskListDisplayed(filteredTaskList), filteredTaskList);
    }
}
