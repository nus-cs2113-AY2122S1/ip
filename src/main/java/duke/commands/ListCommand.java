package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskManager;
import java.util.ArrayList;

/** Includes the operations needed the list the tasks from the current task list. */
public class ListCommand extends Command {

    private static final String LIST_TASKS_MESSAGE = "Wow! I found these tasks in your list:";

    /** Constructed when the command word of the user input is "list". */
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
