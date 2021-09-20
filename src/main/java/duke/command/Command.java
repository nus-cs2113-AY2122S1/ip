package duke.command;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * An abstract class.
 */
public abstract class Command {
    protected boolean isBye;

    /**
     * Abstract method
     */
    abstract public void execute(TaskManager taskManager, Ui ui) throws DukeException;

    /**
     * Returns a boolean indicating whether user has said "bye"
     *
     * @return Boolean isBye indicating whether user has said "bye".
     */
    public boolean getIsBye() {
        return isBye;
    }
}
