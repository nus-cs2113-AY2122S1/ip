package duke.command;

import duke.exception.DukeException;
import duke.util.TaskList;

public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tl) {
        tl.deleteTask(index);
    }
}
