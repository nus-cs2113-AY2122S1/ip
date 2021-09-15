package duke.command;

import duke.exception.DukeException;
import duke.util.TaskList;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tl) {
        try {
            tl.markTaskDone(index);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e.getMessage());
        }
    }
}
