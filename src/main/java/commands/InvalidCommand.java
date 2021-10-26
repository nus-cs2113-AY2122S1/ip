package commands;

import exceptions.DukeException;
import processors.UI;

public class InvalidCommand extends Command{
    public UI ui = new UI();

    /**
     * Function runs due to invalid command from the user and throws an exception
     * @throws DukeException to the user
     */
    public void execute() throws DukeException {
        throw new DukeException("Unknown Command");
    }
}
