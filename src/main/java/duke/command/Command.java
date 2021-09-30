package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents commands to be executed
 */
public abstract class Command {

    /**
     * Executes a given command
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage);

    /**
     * @return returns true if the command to exit the application is given
     */
    public abstract boolean isExit();

}
