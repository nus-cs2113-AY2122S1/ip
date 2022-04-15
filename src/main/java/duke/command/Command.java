package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents all types of command.
 */
public abstract class Command {

    /**
     * A method executes according type of command based on user input.
     *
     * @param tasks TaskList including all tasks.
     * @param ui User Interface.
     * @param storage Storage to load and save the data file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns boolean isExit to indicate whether the command ends the program.
     *
     * @return boolean isExit.
     */
    public abstract boolean isExit();
}
