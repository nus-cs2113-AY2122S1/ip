package duke.command;

import duke.exception.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * Represents an abstract command
 */
public abstract class Command {

    /**
     * The abstract method to be overridden by the subclass of Command to execute
     * different functions.
     * @param tasks the TaskList object that takes in the new deadline task
     * @param ui the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     * @throws DukeException if there is any problem arsing when executing the command
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicate whether to exit and do not take in any new command.
     * @return false by default if the command is not exit.
     */
    public boolean isExit() {
        return false;
    }
}
