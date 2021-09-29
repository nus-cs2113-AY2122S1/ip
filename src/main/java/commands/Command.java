package commands;
import exception.DukeException;
import storage.Storage;
import task.type.TaskList;

public abstract class Command {
    /**
     * Executes necessary methods based on the command.
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
