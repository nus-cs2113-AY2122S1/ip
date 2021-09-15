package duke.command;

import duke.task.Task;
import duke.util.TaskList;

import java.util.ArrayList;

/**
 * Represents the command to find tasks in a list with a given String key.
 */
public class FindCommand extends Command {
    String key;

    public FindCommand(String key) {
        this.key = key;
    }

    /**
     * Finds all the tasks containing the String key in a given list of Tasks.
     * @param tl The list of tasks to be searched.
     */
    @Override
    public void execute(TaskList tl) {
        ArrayList<Task> results = tl.findTasks(key);
        if (results.size() > 0) {
            System.out.println("Here are the tasks matching your key:");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        } else {
            System.out.println("No tasks match your key.");
        }
    }
}
