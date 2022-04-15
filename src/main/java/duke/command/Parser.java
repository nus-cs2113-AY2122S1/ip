package duke.command;

import duke.exception.CommandException;

/**
 * The Parser class deals with understanding the command entered by the user.
 */
public class Parser {

    /* Used to store supported commands */
    private Command[] commandList;

    public Parser(Command[] commandList) {
        this.commandList = commandList;
    }

    /**
     * Finds the correct command according to the given input string.
     *
     * @param inputLine Raw input line to search.
     * @return Command that user is trying to run.
     * @throws CommandException If unable to detect the command in the given input.
     */
    public Command findCommand(String inputLine) throws CommandException {
        for (Command command : commandList) {
            if (command.isCommand(inputLine)) {
                return command;
            }
        }
        throw new CommandException("Command not found");
    }

    /**
     * Parses the user's command and returns the extracted values.
     *
     * @param command   Command that user is trying to run.
     * @param inputLine Raw input line to parse.
     * @return Array containing the extracted argument and flag values.
     * @throws CommandException If unable to correctly parse user's command and arguments.
     */
    public String[] parseCommandLineValues(Command command, String inputLine) throws CommandException {
        if (!command.isValidCommandLine(inputLine)) {
            throw new CommandException("Usage: " + command.getUsage());
        }

        return command.extractCommandLineValues(inputLine);
    }
}
