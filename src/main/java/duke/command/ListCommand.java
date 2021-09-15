package duke.command;

import duke.task.Task;
import duke.util.TaskList;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tl) {
        tl.listTasks();
    }
}
