package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskManager;
import java.util.ArrayList;

public class ListCommand extends Command {

    private static final String LIST_TASKS_MESSAGE = "Wow! I found these tasks in your list:";

    public ListCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        ArrayList<Task> tasks = TaskManager.getTaskList();
        String listOfTasks = TaskManager.listTasks(tasks);
        CommandResult result = new CommandResult(LIST_TASKS_MESSAGE + listOfTasks);
        return result;
    }
}
