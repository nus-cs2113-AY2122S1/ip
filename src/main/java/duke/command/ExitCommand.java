package duke.command;

import duke.DukeException;

/**
 * The command to exit the current execution loop
 * This class implements the <code>Command</code> interface
 */
public class ExitCommand implements Command{
    /**
     * Executes the command
     * @return the exit message
     * @throws DukeException
     */
    @Override
    public String run() throws DukeException {
        String resultMsg = "So long!";
        return resultMsg;
    }
}
