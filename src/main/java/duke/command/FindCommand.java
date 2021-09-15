package duke.command;

import duke.task.Task;
import duke.util.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    String key;

    public FindCommand(String key) {
        this.key = key;
    }

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
