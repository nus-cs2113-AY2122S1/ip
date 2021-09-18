package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.exceptions.DukeException;

public abstract class Command {

    protected Ui ui;
    protected TaskList tasks;
    private int targetIndex = -1;

    /**
     * Constructor for Command
     * @param ui UI to be used
     * @param tasks TaskList to be used
     */
    public Command(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the appropriate command
     * @throws DukeException if no command is specified
     */
    public void execute() throws DukeException {
        throw new DukeException("This method is unspecified!");
    }

}
