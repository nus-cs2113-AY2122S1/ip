package commands;

import duke.DefaultException;
import duke.DukeException;
import tasklist.TaskList;


/**
 * An abstract class with the common methods and attributes for different types of Commands.
 */
public abstract class Command {
    protected TaskList tasks;

    public Command() {
        this.tasks = new TaskList();
    }
    public abstract CommandResult execute() throws DukeException, DefaultException;
    public void passList (TaskList tasks) {
        this.tasks = tasks;
    }
}
