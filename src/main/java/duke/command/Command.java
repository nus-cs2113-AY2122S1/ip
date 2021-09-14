package duke.command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    protected static boolean isExit = false;

    public void hasExecutedExitCommand() {
        isExit = true;
    }

    public static boolean getIsExit() {
        return isExit;
    }

    /**
     * Executes the given command
     * @param taskList Task Manager that executes task based on the given command
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public abstract void executeCommand(TaskList taskList, Storage storage) throws DukeException;
}
