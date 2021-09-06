package duke.command;

import duke.DukeException;
import duke.TaskManager;

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
     * @param taskManager Task Manager that executes task based on the given command
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public abstract void executeCommand(TaskManager taskManager) throws DukeException;
}
