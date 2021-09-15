package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tl);
}
