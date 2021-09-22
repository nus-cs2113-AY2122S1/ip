package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public abstract class Command {
    protected static boolean isOver = false;

    public static boolean isExit() {
        return isOver;
    }

    public abstract void executeUserCommand(TaskList taskList, Storage storage) throws DukeException;
}
