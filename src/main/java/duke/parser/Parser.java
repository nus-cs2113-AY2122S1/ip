package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.Message;

public class Parser {
    public static CommandType getCommandType(String userReponse) {
        String[] params = userReponse.split(" ", 2);
        return CommandType.of(params[0]);
    }

    /**
     * Parses user response to the corresponding command.
     *
     * @param userResponse User response.
     * @return The corresponding command.
     * @throws DukeException If response is invalid.
     */
    public static Command parse(String userResponse) throws DukeException {
        CommandType commandType = getCommandType(userResponse);

        switch (commandType) {
        case DEADLINE:
            return parseDeadlineCommand(userResponse);
        case DELETE:
            return parseDeleteCommand(userResponse);
        case DONE:
            return parseDoneCommand(userResponse);
        case EVENT:
            return parseEventCommand(userResponse);
        case EXIT:
            return parseExitCommand(userResponse);
        case LIST:
            return parseListCommand(userResponse);
        case TODO:
            return parseTodoCommand(userResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseDeadlineCommand(String userResponse) throws DukeException {
        String[] params = userResponse.replace("deadline", "").split("/by");
        if (params.length != 2) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        String description = params[0].strip();
        if (description.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        String by = params[1].strip();
        if (by.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new AddCommand(new Deadline(description, by));
    }

    private static Command parseDeleteCommand(String userResponse) throws DukeException {
        String description = userResponse.replace("delete", "").strip();

        try {
            int taskNumber = Integer.parseInt(description);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.ERROR_NOT_NUMBER);
        }
    }

    private static Command parseDoneCommand(String userResponse) throws DukeException {
        String description = userResponse.replace("done", "").strip();

        try {
            int taskNumber = Integer.parseInt(description);
            return new DoneCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.ERROR_NOT_NUMBER);
        }
    }

    private static Command parseEventCommand(String userResponse) throws DukeException {
        String[] params = userResponse.replace("event", "").split("/at");
        if (params.length != 2) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        String description = params[0].strip();
        if (description.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        String by = params[1].strip();
        if (by.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new AddCommand(new Event(description, by));
    }

    private static Command parseExitCommand(String userResponse) throws DukeException {
        boolean isValidResponse = userResponse.equals("exit");
        if (!isValidResponse) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new ExitCommand();
    }

    private static Command parseListCommand(String userResponse) throws DukeException {
        boolean isValidResponse = userResponse.equals("list");
        if (!isValidResponse) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new ListCommand();
    }

    private static Command parseTodoCommand(String userResponse) throws DukeException {
        String description = userResponse.replace("todo", "").strip();
        if (description.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        return new AddCommand(new Todo(description));
    }
}
