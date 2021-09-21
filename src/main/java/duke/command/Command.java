package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.TaskList;
import duke.text.Text;

public abstract class Command extends Text {

    protected TaskList taskList;
    protected Ui ui;

    /**
     * A constructor for Command.
     *
     * @param taskList user's task list.
     * @param ui the user interface.
     */
    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public Command() {
    }

    /**
     * Executes the command.
     *
     * @throws DukeException exception thrown when Command class is executed.
     */
    public void executed() throws DukeException {
        throw new DukeException("Method is unspecified.");
    }
}
