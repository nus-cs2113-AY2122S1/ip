package duke.commands;

import duke.exceptions.DukeException;

public class EchoCommand extends Command {

    private static final String ECHO_ERROR =
            "OH NO! I can't echo if you don't say anything...";

    public EchoCommand(String argument) {
        super(argument);
    }

    /**
     * Checks if the argument provided by the user is empty.
     *
     * @param argument Argument provided by the user after separating the command word from the user input string
     * @return <code>String</code> to echo on the user interface
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
