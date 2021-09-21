package duke.commands;

import duke.exceptions.DukeException;

/** Includes the operations needed to echo a user input to the user interface. */
public class EchoCommand extends Command {

    private static final String ECHO_ERROR =
            "OH NO! I can't echo if you don't say anything...";

    /**
     * Constructed when the command word of the user input is "echo".
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     */
    public EchoCommand(String argument) {
        super(argument);
    }

    /**
     * Checks if the argument provided by the user is empty.
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @return string to echo on the user interface
     * @throws DukeException If the argument specified is empty
     */
    private String retrieveEchoParameter(String argument) throws DukeException {
        if (isEmptyArgument(argument)) {
            throw new DukeException(ECHO_ERROR);
        }
        return argument;
    }

    @Override
    public CommandResult executeCommand() throws DukeException {
        String parameter = retrieveEchoParameter(argument);
        CommandResult result = new CommandResult(parameter);
        return result;
    }
}
