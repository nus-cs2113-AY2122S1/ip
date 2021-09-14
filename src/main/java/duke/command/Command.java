package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    /**
     * Returns if the command type is the exitCommand
     *
     * @return boolean value of whether the command is the exitCommand
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Abstract method that executes the command
     *
     * @param taskList the taskManager that will be modified (by most Command subclasses)
     * @param ui The ui class instance that will print out the completion message (by most Command subclasses)
     * @throws DukeException exception that will be thrown by some Command subclasses
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException;
}
