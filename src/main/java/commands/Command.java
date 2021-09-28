package commands;
import exception.DukeException;
import storage.Storage;
import task.type.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
