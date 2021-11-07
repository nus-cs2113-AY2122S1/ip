package command;

import exceptions.DukeInvalidArgumentException;
import storage.Storage;
import task.TaskList;

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    /**
     * Format for subclasses to execute methods.
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeInvalidArgumentException;

    /**
     * Format for isExit() method for subclasses.
     */
    public abstract boolean isExit();
}
