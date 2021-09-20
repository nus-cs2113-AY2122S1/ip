package duke.command;


import duke.tasks.TaskList;
import duke.exception.DukeException;


/**
 * Abstract command class.
 *
 */
public abstract class Command {
    protected Boolean isBye = false;
    protected String command;
    protected String date;
    protected String returnString;

    /**
     * Executes the command
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks) throws DukeException;

    /**
     * Returns the boolean isBye as a way to tell Logic class when is it appropriate to exit the while loop.
     *
     * @return boolean isBye.
    */
    public boolean getExitStatus() {
        return isBye;
    }
}
