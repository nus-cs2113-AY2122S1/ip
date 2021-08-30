package shikabot.parser;

import shikabot.command.AddCommand;
import shikabot.command.Command;
import shikabot.command.DeleteCommand;
import shikabot.command.DoneCommand;
import shikabot.command.ExitCommand;
import shikabot.command.FailedCommand;
import shikabot.command.InvalidCommand;
import shikabot.command.ListCommand;
import shikabot.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Parser {

    public static final int INVALID_DEADLINE_SYNTAX = 1;
    public static final int INVALID_EVENT_SYNTAX = 2;
    public static final int NUMBER_FORMAT_ERROR = 3;
    public static final int NEGATIVE_INDEX_ERROR = 4;
    public static final int INVALID_TASK = 5;
    public static final int INVALID_DATE_SYNTAX = 6;

    public boolean isAddCommand(String text) {
        return text.startsWith("todo") || text.startsWith("deadline") || text.startsWith("event");
    }

    public Command parseCommand(String text) {
        Command command;
        text = text.trim();
        if (text.equals("bye")) {
            command = new ExitCommand();
        } else if (text.equals("list")) {
            command = new ListCommand();
        } else if (text.startsWith("done")) {
            command = parseDoneCommand(text);
        } else if (text.startsWith("delete")) {
            command = parseDeleteCommand(text);
        } else if (isAddCommand(text)) {
            command = parseAddCommand(text);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    private Command parseDoneCommand(String text) {
        String str = text.substring(text.indexOf("done") + 4).trim();
        int index;
        try {
            index = Integer.parseInt(str) - 1;
        } catch (NumberFormatException e) {
            return new FailedCommand(NUMBER_FORMAT_ERROR);
        }
        if (index < 0) {
            return new FailedCommand(NEGATIVE_INDEX_ERROR);
        }
        try {
            return new DoneCommand(index);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(INVALID_TASK);
        }
    }

    private Command parseDeleteCommand(String text) {
        String str = text.substring(text.indexOf("delete") + 6).trim();
        int index;
        try {
            index = Integer.parseInt(str) - 1;
        } catch (NumberFormatException e) {
            return new FailedCommand(NUMBER_FORMAT_ERROR);
        }
        if (index < 0) {
            return new FailedCommand(NEGATIVE_INDEX_ERROR);
        }
        try {
            return new DeleteCommand(index);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(INVALID_TASK);
        }
    }

    private Command parseAddCommand(String text) {
        Command command;
        if (text.startsWith("todo")) {
            command = parseAddTodoCommand(text);
        } else if (text.startsWith("deadline")) {
            try {
                command = parseAddDeadlineCommand(text);
            } catch (Task.InvalidTaskException e) {
                return new FailedCommand(INVALID_DEADLINE_SYNTAX);
            } catch (InvalidDateException e) {
                return new FailedCommand(INVALID_DATE_SYNTAX);
            }
        } else {
            try {
                command = parseAddEventCommand(text);
            } catch (Task.InvalidTaskException e) {
                return new FailedCommand(INVALID_EVENT_SYNTAX);
            } catch (InvalidDateException e) {
                return new FailedCommand(INVALID_DATE_SYNTAX);
            }
        }
        return command;
    }

    private Command parseAddTodoCommand(String text) {
        String name = text.substring(text.indexOf("todo") + 4).trim();
        return new AddCommand('T', name, null);
    }

    private Command parseAddDeadlineCommand(String text) throws Task.InvalidTaskException, InvalidDateException {
        if (!text.contains("/by")) {
            throw new Task.InvalidTaskException();
        }
        String name = text.substring(text.indexOf("deadline") + 8, text.indexOf("/")).trim();
        String date = text.substring(text.indexOf("/by") + 3).trim();
        try {
            LocalDate by = LocalDate.parse(date);
            return new AddCommand('D', name, by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    private Command parseAddEventCommand(String text) throws Task.InvalidTaskException, InvalidDateException {
        if (!text.contains("/at")) {
            throw new Task.InvalidTaskException();
        }
        String name = text.substring(text.indexOf("event") + 5, text.indexOf("/")).trim();
        String date = text.substring(text.indexOf("/at") + 3).trim();
        try {
            LocalDate at = LocalDate.parse(date);
            return new AddCommand('E', name, at);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    private class InvalidDateException extends Throwable {
    }
}
