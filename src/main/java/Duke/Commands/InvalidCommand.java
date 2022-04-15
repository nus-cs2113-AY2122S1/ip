package Duke.Commands;

import Duke.DukeException;

public class InvalidCommand extends Command{

    private static final String INVALID_INPUT_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means." + System.lineSeparator()
            + "\tPlease enter a valid input!" + System.lineSeparator()
            + "\ti.e. todo, deadline, event, list, done or bye.";

    /**
     * Throws an invalid input message.
     * @throws DukeException When this command type is being initialised.
     */
    public InvalidCommand() throws DukeException {
        throw new DukeException(INVALID_INPUT_MESSAGE);
    }
}
