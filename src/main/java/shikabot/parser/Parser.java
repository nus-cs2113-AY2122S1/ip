package shikabot.parser;

import shikabot.command.AddCommand;
import shikabot.command.Command;
import shikabot.command.DeleteCommand;
import shikabot.command.DoneCommand;
import shikabot.command.ExitCommand;
import shikabot.command.FailedCommand;
import shikabot.command.FindCommand;
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

    private static final int FIND_LENGTH = 4;
    private static final int DONE_LENGTH = 4;
    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;
    private static final int DELETE_LENGTH = 6;
    private static final int DIVIDER_LENGTH = 3;

    private static final String NOTHING = "";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DIVIDER = "/";
    private static final String AT = "/at";
    private static final String BY = "/by";

    /**
     * Function that checks if the string entered is an add command.
     * @param text String to be parsed.
     * @return true if String starts with todo, deadline or event. False otherwise.
     */
    public boolean isAddCommand(String text) {
        text = text.toLowerCase();
        return text.startsWith("todo") || text.startsWith("deadline") || text.startsWith("event");
    }

    /**
     * Function that parses the command. Calls other functions in order to parse or execute the actual command.
     * @param text String to be parsed.
     * @return command to be executed.
     */
    public Command parseCommand(String text) {
        Command command;
        text = text.trim();
        String lowerCaseText = text.toLowerCase();
        if (text.equalsIgnoreCase("bye")) {
            command = new ExitCommand();
        } else if (text.equalsIgnoreCase("list")) {
            command = new ListCommand();
        } else if (lowerCaseText.startsWith("done")) {
            command = parseDoneCommand(text);
        } else if (lowerCaseText.startsWith("delete")) {
            command = parseDeleteCommand(text);
        } else if (lowerCaseText.startsWith("find")) {
            command = parseFindCommand(text);
        } else if (isAddCommand(text)) {
            command = parseAddCommand(text);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    /**
     * This function assumes that the input command is a done command and attempts to parse it. Will return
     * a DoneCommand if command is correct, otherwise it will return a FailedCommand.
     * @param text String to be parsed.
     * @return command to be executed.
     */
    private Command parseDoneCommand(String text) {
        String str = text.substring(DONE_LENGTH).trim();
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

    /**
     * This function assumes that the input command is a delete command and attempts to parse it. Will return
     * a DeleteCommand if command is correct, otherwise it will return a FailedCommand.
     * @param text String to be parsed.
     * @return command to be executed.
     */
    private Command parseDeleteCommand(String text) {
        String str = text.substring(DELETE_LENGTH).trim();
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

    /**
     * This function assumes that the input command is a find command and attempts to parse it. Will return
     * a FindCommand.
     * @param text String to search for.
     * @return command to be executed.
     */
    private Command parseFindCommand(String text) {
        String str = text.substring(FIND_LENGTH).trim();
        return new FindCommand(str);
    }
  
    /**
     * This function assumes that the input command is an add command and attempts to parse it. Will return
     * an AddCommand if command is correct, otherwise it will return a FailedCommand.
     * @param text String to be parsed.
     * @return command to be executed.
     */
    private Command parseAddCommand(String text) {
        Command command;
        if (text.toLowerCase().startsWith(TODO)) {
            try {
                command = parseAddTodoCommand(text);
            } catch (EmptyFieldException e) {
                return new FailedCommand(EMPTY_FIELD);
            }
        } else if (text.toLowerCase().startsWith(DEADLINE)) {
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

    /**
     * This function assumes that the input command is an add todo command and attempts to parse it. Will return
     * an AddCommand if command is correct.
     * @return command to be executed.
     * @throws EmptyFieldException if any fields are left empty.
     */
    private Command parseAddTodoCommand(String text) throws EmptyFieldException {
        String name = text.substring(TODO_LENGTH).trim();
        if (name.equals(NOTHING)) {
            throw new EmptyFieldException();
        }
        return new AddCommand('T', name, null);
    }

    /**
     * This function assumes that the input command is an add deadline command and attempts to parse it. Will return
     * an AddCommand if command is correct.
     * @param text String to be parsed.
     * @return command to be executed.
     * @throws EmptyFieldException if any fields are left empty.
     * @throws Task.InvalidTaskException if syntax is wrong.
     * @throws InvalidDateException if date is in invalid format.
     */
    private Command parseAddDeadlineCommand(String text) throws EmptyFieldException, Task.InvalidTaskException, InvalidDateException {
        if (!text.toLowerCase().contains(BY)) {
            throw new Task.InvalidTaskException();
        }
        String name = text.substring(DEADLINE_LENGTH, text.indexOf(DIVIDER)).trim();
        String date = text.substring(text.toLowerCase().indexOf(BY) + DIVIDER_LENGTH).trim();
        if (name.equals(NOTHING) || date.equals(NOTHING)) {
            throw new EmptyFieldException();
        }
        try {
            LocalDate by = LocalDate.parse(date, TIME_PARSER);
            return new AddCommand('D', name, by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * This function assumes that the input command is an add event command and attempts to parse it. Will return
     * an AddCommand if command is correct.
     * @param text String to be parsed.
     * @return command to be executed.
     * @throws EmptyFieldException if any fields are left empty.
     * @throws Task.InvalidTaskException if syntax is wrong.
     * @throws InvalidDateException if date is in invalid format.
     */
    private Command parseAddEventCommand(String text) throws EmptyFieldException, Task.InvalidTaskException, InvalidDateException {
        if (!text.toLowerCase().contains(AT)) {
            throw new Task.InvalidTaskException();
        }
        String name = text.substring(EVENT_LENGTH, text.indexOf(DIVIDER)).trim();
        String date = text.substring(text.toLowerCase().indexOf(AT) + DIVIDER_LENGTH).trim();
        if (name.equals(NOTHING) || date.equals(NOTHING)) {
            throw new EmptyFieldException();
        }
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
