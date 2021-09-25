package commands;

import duke.DefaultException;
import duke.DukeException;
import tasklist.TaskList;

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
