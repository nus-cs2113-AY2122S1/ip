package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.TaskList;
import duke.text.Text;

public abstract class Command extends Text {

    protected TaskList taskList;
    protected Ui ui;

    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public Command() {
    }

    public void execute() throws DukeException {
        throw new DukeException(UNSPECIFIED_METHOD);
    }
}
