package duke.command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;

/**
 * Abstract class that defines the behaviour of all command objects
 */
public abstract class Command {
    protected static boolean isExit = false;

    /**
     * Sets isExit status when the Exit command is executed
     */
    public void hasExecutedExitCommand() {
        isExit = true;
    }

    /**
     * Checks whether the command object is an exit command
     * @return Returns true if current command object is exit command, false otherwise
     */
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
