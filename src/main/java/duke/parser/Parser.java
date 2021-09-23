package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DateCommand;
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
        case DATE:
            return parseDateCommand(userResponse);
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

    private static Command parseDateCommand(String userResponse) throws DukeException {
        String param = userResponse.replaceFirst("date", "").strip();

        try {
            LocalDate date = LocalDate.parse(param);
            return new DateCommand(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } catch (DateTimeParseException e) {
            throw new DukeException(Message.ERROR_INVALID_DATE);
        }
    }

    private static Command parseDeadlineCommand(String userResponse) throws DukeException {
        String[] params = userResponse.replaceFirst("deadline", "").split("/by");
        if (params.length != 2) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        String description = params[0].strip();
        if (description.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        String taskDeadline = params[1].strip();
        if (taskDeadline.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        try {
            LocalDate date = LocalDate.parse(taskDeadline);
            return new AddCommand(new Deadline(description,
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
        } catch (DateTimeParseException e) {
            return new AddCommand(new Deadline(description, taskDeadline));
        }
    }

    private static Command parseDeleteCommand(String userResponse) throws DukeException {
        String description = userResponse.replaceFirst("delete", "").strip();

        try {
            int taskNumber = Integer.parseInt(description);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.ERROR_NOT_NUMBER);
        }
    }

    private static Command parseDoneCommand(String userResponse) throws DukeException {
        String description = userResponse.replaceFirst("done", "").strip();

        try {
            int taskNumber = Integer.parseInt(description);
            return new DoneCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(Message.ERROR_NOT_NUMBER);
        }
    }

    private static Command parseEventCommand(String userResponse) throws DukeException {
        String[] params = userResponse.replaceFirst("event", "").split("/at");
        if (params.length != 2) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        String description = params[0].strip();
        if (description.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        String taskPeriod = params[1].strip();
        if (taskPeriod.isBlank()) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND);
        }

        try {
            LocalDate date = LocalDate.parse(taskPeriod);
            return new AddCommand(new Event(description,
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
        } catch (DateTimeParseException e) {
            return new AddCommand(new Event(description, taskPeriod));
        }
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
