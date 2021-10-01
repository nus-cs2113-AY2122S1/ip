package duke.parser;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.exception.DukeException;
import duke.command.*;

public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    /**
     * Determine the command type of the command which is input by the user
     * If the command type is unknown, DukeException is thrown
     *
     * @param command  The input by the user
     * @return the corresponding command type
     * @throws DukeException if the command does not match any known command type
     */
    public static String getCommandType(String command) throws DukeException{
        if (command.contains("done")) {
            return "DONE";
        } else if (command.contains("delete")) {
            return "DELETE";
        } else if (command.contains("bye")) {
            return "BYE";
        } else if (command.contains("list")) {
            return "LIST";
        } else if (command.contains("todo")) {
            return "T";
        } else if (command.contains("deadline")) {
            return "D";
        } else if (command.contains("event")) {
            return "E";
        } else if (command.contains("find")) {
            return "FIND";
        } else if (command.contains("help")) {
            return "HELP";
        } else {
            throw new DukeException();
        }
    }

    /**
     * Parses the full command given by the user as input.
     *
     * @param command The input by the user that is to be parsed.
     * @return Command that corresponds to the user input.
     * @throws DukeException If the command is invalid.
     */
    public static Command parse(String command) throws DukeException {
        try {
            String commandType = getCommandType(command);
            switch (commandType) {
            case "DELETE":
                return new DeleteCommand(command);
            case "DONE":
                return new DoneCommand(command);
            case "BYE":
                return new ByeCommand();
            case "LIST":
                return new ListCommand();
            case "FIND":
                return new FindCommand(command);
            case "HELP":
                return new HelpCommand();
            default:
                return new AddCommand(command);
            }
        } catch (DukeException e) {
            throw new DukeException();
        }
    }
}
