package Duke;

import Duke.commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Duke.Constants.*;
import static Duke.Constants.COMMAND_BYE;

public class Parser {
    private static final String DEADLINE_BY_PREFIX = "/by ";
    private static final String EVENT_AT_PREFIX = "/at ";

    public static Command parse(String input) throws DukeException {
        String[] commandAndParams = splitCommandString(input, " ");
        String command = commandAndParams[0];
        String params = commandAndParams[1];
        switch (command) {
        case COMMAND_TODO:
            try {
                return parseTodo(params);
            } catch (DukeException e) {
                return new IncorrectCommand(COMMAND_TODO);
            }
        case COMMAND_DEADLINE:
            try {
                return parseDeadline(params);
            } catch (DukeException e) {
                return new IncorrectCommand(COMMAND_DEADLINE);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand(DATEANDTIME);
            }
        case COMMAND_EVENT:
            try {
                return parseEvent(params);
            } catch (DukeException e) {
                return new IncorrectCommand(COMMAND_EVENT);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand(DATEANDTIME);
            }
        case COMMAND_DONE:
            try {
                return parseDone(params);
            } catch(DukeException e) {
                return new IncorrectCommand(COMMAND_DONE);
            }
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_DELETE:
            try {
                return parseDelete(params);
            } catch (DukeException e){
                return new IncorrectCommand(COMMAND_DELETE);
            }
        case COMMAND_FIND:
            try {
                return parseFind(params);
            } catch (DukeException e) {
                return new IncorrectCommand(COMMAND_FIND);
            }
        case COMMAND_DATE:
            return parseDate(params);
        case COMMAND_BYE:
            return new ByeCommand();
        default:
            throw new DukeException();
        }
    }

    private static Command parseTodo(String params) throws DukeException {
        if (params.equals("")) {
            throw new DukeException();
        }
        Todo t = new Todo(params);
        return new AddCommand(t);
    }

    private static Command parseDeadline(String params) throws DukeException, DateTimeParseException {
        if (params.equals("")) {
            throw new DukeException();
        }
        String[] descAndBy = splitCommandString(params, DEADLINE_BY_PREFIX);
        String description = descAndBy[0];
        String by = descAndBy[1];
        Deadline d = new Deadline(description, by);
        return new AddCommand(d);
    }

    private static Command parseEvent(String params) throws DukeException, DateTimeParseException {
        if (params.equals("")) {
            throw new DukeException();
        }
        String[] descAndAt = splitCommandString(params, EVENT_AT_PREFIX);
        String description = descAndAt[0];
        String at = descAndAt[1];
        Event e = new Event(description, at);
        return new AddCommand(e);
    }

    private static Command parseDone(String params) throws DukeException {
            int taskNumber = findTaskNumber(params);
            return new DoneCommand(taskNumber);
    }

    private static Command parseDelete(String params) throws DukeException {
        int taskNumber = findTaskNumber(params);
        return new DeleteCommand(taskNumber);
    }

    private static Command parseFind(String params) throws DukeException {
        if (params.equals("")) {
            throw new DukeException();
        }
        return new FindCommand(params);
    }

    private static Command parseDate(String params) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(params, formatter);
        return new DateCommand(dateTime);
    }

    private static int findTaskNumber(String params) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(params);
            if (taskNumber > Task.getTaskCount()) {
                throw new DukeException();
            }
            return (taskNumber - 1);
        } catch (NumberFormatException e){
            throw new DukeException();
        }
    }

    private static String[] splitCommandString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[] {split[0], ""};
    }
}
