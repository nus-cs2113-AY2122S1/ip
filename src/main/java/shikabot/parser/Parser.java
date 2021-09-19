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

public class Parser {

    public static final int INVALID_DEADLINE_SYNTAX = 1;
    public static final int INVALID_EVENT_SYNTAX = 2;
    public static final int NUMBER_FORMAT_ERROR = 3;
    public static final int NEGATIVE_INDEX_ERROR = 4;
    public static final int INVALID_TASK = 5;
    public static final int INVALID_DATE_SYNTAX = 6;
    public static final int EMPTY_FIELD = 7;

    private static final DateTimeFormatter TIME_PARSER =
            DateTimeFormatter.ofPattern("[ddMMyyyy][dd/MM/yyyy][dd-MM-yyyy]");

    private static final int DONE_LENGTH = 4;
    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;
    private static final int DELETE_LENGTH = 6;
    private static final int DIVIDER_LENGTH = 3;

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
        String str = text.substring(text.indexOf("done") + DONE_LENGTH).trim();
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
        String str = text.substring(text.indexOf("delete") + DELETE_LENGTH).trim();
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
            try {
                command = parseAddTodoCommand(text);
            } catch (EmptyFieldException e) {
                return new FailedCommand(EMPTY_FIELD);
            }
        } else if (text.startsWith("deadline")) {
            try {
                command = parseAddDeadlineCommand(text);
            } catch (Task.InvalidTaskException e) {
                return new FailedCommand(INVALID_DEADLINE_SYNTAX);
            } catch (InvalidDateException e) {
                return new FailedCommand(INVALID_DATE_SYNTAX);
            } catch (EmptyFieldException e) {
                return new FailedCommand(EMPTY_FIELD);
            }
        } else {
            try {
                command = parseAddEventCommand(text);
            } catch (Task.InvalidTaskException e) {
                return new FailedCommand(INVALID_EVENT_SYNTAX);
            } catch (InvalidDateException e) {
                return new FailedCommand(INVALID_DATE_SYNTAX);
            } catch (EmptyFieldException e) {
                return new FailedCommand(EMPTY_FIELD);
            }
        }
        return command;
    }

    private Command parseAddTodoCommand(String text) throws EmptyFieldException {
        String name = text.substring(text.indexOf("todo") + TODO_LENGTH).trim();
        if (name.equals("")) throw new EmptyFieldException();
        return new AddCommand('T', name, "");
    }

    private Command parseAddDeadlineCommand(String text) throws EmptyFieldException, Task.InvalidTaskException {
        if (!text.contains("/by")) {
            throw new Task.InvalidTaskException();
        }
        String name = text.substring(text.indexOf("deadline") + DEADLINE_LENGTH, text.indexOf("/")).trim();
        String by = text.substring(text.indexOf("/by") + DIVIDER_LENGTH).trim();
        if (name.equals("") || by.equals("")) throw new EmptyFieldException();
        try {
            LocalDate by = LocalDate.parse(date, TIME_PARSER);
            return new AddCommand('D', name, by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    private Command parseAddEventCommand(String text) throws EmptyFieldException, Task.InvalidTaskException {
        if (!text.contains("/at")) {
            throw new Task.InvalidTaskException();
        }
        String name = text.substring(text.indexOf("event") + EVENT_LENGTH, text.indexOf("/")).trim();
        String at = text.substring(text.indexOf("/at") + DIVIDER_LENGTH).trim();
        if (name.equals("") || at.equals("")) throw new EmptyFieldException();
        try {
            LocalDate at = LocalDate.parse(date,TIME_PARSER);
            return new AddCommand('E', name, at);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    private static class InvalidDateException extends Throwable {
    }
}
