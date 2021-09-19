package duke;


import duke.exception.DukeException;
import duke.tasks.Task;
import java.util.ArrayList;


/**
 * Command takes in the parsed input from the user and splits it into three categories.
 * They are the command type, description of the task and the date. Depending on the
 * type of command, description and date may be empty. Command then returns the
 * appropriate mesage to return to Logic class to output to the user.
 *
 * @param "commandInput"  type of command.
 * @param "descriptionInput" Y description of task.
 * @param "dateInput" event date/deadline.
 * @return output message to the user.
 */
public abstract class Command {
    protected Boolean isBye = false;
    protected String command;
    protected String date;
    protected String returnString;

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
