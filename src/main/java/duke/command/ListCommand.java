package duke.command;

import duke.task.Task;
import duke.util.TaskList;

/**
 * Represents the command to print all Tasks in a list of Tasks.
 */
public class ListCommand extends Command{
    /**
     * Prints all Tasks in the given list of Tasks to the command line.
     * @param tl The list of tasks to be printed.
     */
    @Override
    public void execute(TaskList tl) {
        tl.listTasks();
    }
}
