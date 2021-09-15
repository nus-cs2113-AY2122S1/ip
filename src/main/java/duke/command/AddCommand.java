package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;

public class AddCommand extends Command {
    Task addedTask;

    public AddCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

    @Override
    public void execute(TaskList tl) {
        try {
            tl.addNewTask(addedTask);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e.getMessage());
        }
    }
}
