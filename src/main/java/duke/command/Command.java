package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.exceptions.DukeException;

public abstract class Command {

    protected Ui ui;
    protected TaskList tasks;
    private int targetIndex = -1;

    public Command(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    public void execute() throws DukeException {
        throw new DukeException("This method is unspecified!");
    }

}
