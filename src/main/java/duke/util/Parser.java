package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Task;

public class Parser {
    private static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";

    /**
     * Parses the userInput and returns a Command object.
     *
     * @param userInput The string to parse.
     * @return A Command object.
     * @throws DukeException if command is unknown.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] commandAndArgument = getCommandAndArgument(userInput);
        String commandString = commandAndArgument[0];
        String argument = (commandAndArgument.length == 2) ? commandAndArgument[1] : "";

        Command command;
        switch (commandString) {
        case Command.COMMAND_LIST:
            command = new ListCommand(argument);
            break;

        case Command.COMMAND_BYE:
            command = new ExitCommand(argument);
            break;

        case Command.COMMAND_DONE:
            command = new DoneCommand(argument);
            break;

        case Command.COMMAND_DELETE:
            command = new DeleteCommand(argument);
            break;

        case Command.COMMAND_FIND:
            command = new FindCommand(argument);
            break;

        case Command.COMMAND_TODO:
            command = new AddCommand(argument, Task.TYPE_TODO);
            break;

        case Command.COMMAND_DEADLINE:
            command = new AddCommand(argument, Task.TYPE_DEADLINE);
            break;

        case Command.COMMAND_EVENT:
            command = new AddCommand(argument, Task.TYPE_EVENT);
            break;

        default:
            throw new DukeException(MESSAGE_UNKNOWN_COMMAND);
        }

        return command;
    }

    /**
     * Splits user input string into command and argument.
     *
     * @param input The user input string.
     * @return String array: [0] - Command, [1] - argument.
     */
    private static String[] getCommandAndArgument(String input) {
        String[] result = input.trim().split("\\s+", 2);
        if (result.length != 2) {
            return new String[]{result[0], ""};
        }

        return result;
    }
}
