package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;

/**
 * Parses user input.
 */
public class Parser {
    // Code below inspired by
    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
    private static final String CAPTURING_GROUP_COMMAND = "command";
    private static final String CAPTURING_GROUP_ARGS = "args";
    /** Used for initial separation of command word and args. */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "(?<" + CAPTURING_GROUP_COMMAND + ">\\S+)"
                    + "(?<" + CAPTURING_GROUP_ARGS + ">.*)");

    public static final String CAPTURING_GROUP_DESCRIPTION = "description";
    public static final String DELIMITER_BY = "/by";
    public static final String DELIMITER_AT = "/at";
    public static final String CAPTURING_GROUP_BY = "by";
    public static final String CAPTURING_GROUP_AT = "at";
    public static final Pattern TASK_ARGS_FORMAT = Pattern.compile(
            "(?<" + CAPTURING_GROUP_DESCRIPTION + ">[^/]+)"
                    + "( " + DELIMITER_BY + " (?<" + CAPTURING_GROUP_BY + ">[^/]+))?"
                    + "( " + DELIMITER_AT + " (?<" + CAPTURING_GROUP_AT + ">[^/]+))?");

    private static final String MESSAGE_TODO_DESCRIPTION_EMPTY = "The description of a todo cannot be empty.";
    private static final String MESSAGE_UNRECOGNISED_EVENT_FORMAT = "Unrecognised event format.\n"
            + "Please ensure you provide the date/time of the event.";
    private static final String MESSAGE_UNRECOGNISED_DEADLINE_FORMAT = "Unrecognised deadline format.\n"
            + "Please ensure you provide the date/time of the deadline.";
    private static final String MESSAGE_INVALID_TASK_NUMBER = "Please use a valid integer for the task number.";
    private static final String MESSAGE_UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :-(";

    /**
     * Parses user input as a command.
     *
     * @param userInput Input command together with any arguments.
     * @return Command that corresponds to the user input, if valid.
     * @throws DukeException If input is of an invalid command format.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new DukeException("Invalid command format!");
        }
        final String command = matcher.group(CAPTURING_GROUP_COMMAND);
        final String args = matcher.group(CAPTURING_GROUP_ARGS).trim();

        switch (command) {
        case ToDoCommand.COMMAND_WORD:
            return prepareToDo(args);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(args);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(args);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(parseTaskId(args));
        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(parseTaskId(args));
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            return handleUnrecognisedCommand();
        }
    }

    private static Command prepareToDo(String args) throws DukeException {
        final Matcher matcher;
        try {
            matcher = parseTask(args);
        } catch (DukeException e) {
            throw new DukeException(MESSAGE_TODO_DESCRIPTION_EMPTY);
        }
        final String description = matcher.group(CAPTURING_GROUP_DESCRIPTION);
        if (description == null || description.isBlank()) {
            throw new DukeException("Invalid command format!");
        }
        return new ToDoCommand(description);
    }

    private static Command prepareDeadline(String args) throws DukeException {
        final Matcher matcher = parseTask(args);
        final String description = matcher.group(CAPTURING_GROUP_DESCRIPTION);
        final String by = matcher.group(CAPTURING_GROUP_BY);
        if (description == null || description.isBlank() || by == null || by.isBlank()) {
            throw new DukeException(MESSAGE_UNRECOGNISED_DEADLINE_FORMAT);
        }
        return new DeadlineCommand(description, by);
    }

    private static Command prepareEvent(String args) throws DukeException {
        final Matcher matcher = parseTask(args);
        final String description = matcher.group(CAPTURING_GROUP_DESCRIPTION);
        final String at = matcher.group(CAPTURING_GROUP_AT);
        if (description == null || description.isBlank() || at == null || at.isBlank()) {
            throw new DukeException(MESSAGE_UNRECOGNISED_EVENT_FORMAT);
        }
        return new EventCommand(description, at);
    }

    private static Matcher parseTask(String args) throws DukeException {
        final Matcher matcher = TASK_ARGS_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new DukeException("Invalid command format!");
        }
        return matcher;
    }

    /**
     * Parses task id string as an integer.
     *
     * @param taskIdString Task id string to be converted to an integer.
     * @return Task id parsed as an integer.
     * @throws DukeException If string is not of a proper integer format.
     */
    private static int parseTaskId(String taskIdString) throws DukeException {
        try {
            return Integer.parseInt(taskIdString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }
    }

    private static Command handleUnrecognisedCommand() throws DukeException {
        throw new DukeException(MESSAGE_UNRECOGNISED_COMMAND);
    }
}
