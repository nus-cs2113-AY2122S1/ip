package duke;

import Command.AddCommand;
import Command.Command;
import Command.DeleteCommand;
import Command.DoneCommand;
import Command.ExitCommand;
import Command.FindCommand;
import Command.ListCommand;

/**
 * Represents an object to parse user inputs.
 */
public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    /**
     * Parse the user input to Command object.
     *
     * @param fullCommand Full user input.
     * @return Command object representing the user input.
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals(COMMAND_BYE)) {
            return new ExitCommand(fullCommand);
        }

        if (fullCommand.equals(COMMAND_LIST)) {
            return new ListCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_DONE)) {
            return new DoneCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_DELETE)) {
            return new DeleteCommand(fullCommand);
        }

        if (fullCommand.startsWith(COMMAND_FIND)) {
            return new FindCommand(fullCommand);
        }

        return new AddCommand(fullCommand);
    }
}
