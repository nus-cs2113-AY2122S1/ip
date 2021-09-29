package duke.util;

import duke.DukeException;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class represents an utility to parse command from the user's input
 */
public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_EXIT = "bye";

    private static final String INVALID_COMMAND = "Not a recognisable command by me. Try \"help\" instead!";

    private static final String COMMAND_DEADLINE_SEPARATOR = "/by";
    private static final String COMMAND_EVENT_SEPARATOR = "/at";

    private static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm";
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private static final String SPACE_SEPARATOR = " ";
    private static final String EMPTY_STRING = "";

    private static final int EVENT_DEADLINE_ARGUMENT_COUNT = 2;

    /**
     * Checks to see if the user has inputted an exit command
     *
     * @param command the user's input command
     * @return true if the user's command is exit command, false otherwise
     */
    public static boolean isExit(String command) {
        return (command.equals(COMMAND_EXIT));
    }


    /**
     * Parse the user's input command to the corresponding command
     *
     * @param command user's input command
     * @return the corresponding command that follows the <code>Command</code> interface
     * @throws DukeException if the command cannot be recognised
     */
    public static Command parse(String command) throws DukeException {
        String[] words = command.split(SPACE_SEPARATOR);

        switch (words[0]) {
        case COMMAND_LIST:
            return parseListCommand();
        case COMMAND_TODO:
            return parseTodoCommand(command);
        case COMMAND_EVENT:
            return parseEventCommand(command);
        case COMMAND_DEADLINE:
            return parseDeadlineCommand(command);
        case COMMAND_DONE:
            return parseDoneCommand(command);
        case COMMAND_DELETE:
            return parseDeleteCommand(command);
        case COMMAND_EXIT:
            return parseExitCommand();
        case COMMAND_FIND:
            return parseFindCommand(command);
        case COMMAND_HELP:
            return parseHelpCommand(command);
        default:
            throw new DukeException(INVALID_COMMAND);
        }
    }

    /**
     * A private method to parse delete command
     *
     * @param command user's input command
     * @return a command instance that follows the <code>Command</code> interface
     * @throws DukeException if the list is empty, or the command does not contain a number
     */
    private static Command parseDeleteCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_DELETE.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Nothing to delete!");
        }

        try {
            int taskDeleteNumber = Integer.parseInt(detail);
            return new DeleteCommand(taskDeleteNumber);
        } catch (NumberFormatException e) {
            throw new DukeException("Uhm that definitely not a number bro. Pick again.");
        }
    }

    /**
     * A private method to parse find command
     *
     * @param command user's input command
     * @return a command instance that follows the <code>Command</code> interface
     * @throws DukeException if the find command has no keyword
     */
    private static Command parseFindCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_FIND.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Nothing to find!");
        }
        return new FindCommand(detail);
    }

    /**
     * A private method to parse exit command
     *
     * @return a command instance that follows the <code>Command</code> interface
     */
    private static Command parseExitCommand() {
        return new ExitCommand();
    }

    /**
     * A private method to parse list command
     *
     * @return a command instance that follows the <code>Command</code> interface
     */
    private static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * A private method to parse todo command
     *
     * @param command user's input command
     * @return a command instance that follows the <code>Command</code> interface
     * @throws DukeException if the task description is empty
     */
    private static Command parseTodoCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_TODO.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Bro please let me know what thing you gonna do");
        }

        return new AddCommand(new ToDos(detail, false));
    }

    /**
     * A private method to parse deadline command
     *
     * @param command user's input command
     * @return a command instance that follows the <code>Command</code> interface
     * @throws DukeException if the task description is empty, or the date time format is invalid
     */
    private static Command parseDeadlineCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_DEADLINE.length()).trim();
        String[] contentAndDate = detail.split(COMMAND_DEADLINE_SEPARATOR);

        if (detail.length() <= 0 || contentAndDate.length <= 0) {
            throw new DukeException("Invalid format. Enter by this format:\n"
                    + "\t\t\"deadline [description] /by [deadline]\"");
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            if (contentAndDate.length != EVENT_DEADLINE_ARGUMENT_COUNT
                    || contentAndDate[i].equals(EMPTY_STRING)) {
                throw new DukeException("Invalid format. Enter by this format:\n"
                        + "\t\t\"deadline [description] /by [deadline]\"");
            }
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            contentAndDate[i] = contentAndDate[i].trim();
        }
        try {
            LocalDateTime deadline = LocalDateTime.parse(contentAndDate[1], dateTimeFormat);
            return new AddCommand(new Deadline(contentAndDate[0], deadline, false));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write the date in this format: " + DATE_TIME_FORMAT + '\n'
                    + "\t\te.g: 20 Sep 2021 23:59");
        }
    }

    /**
     * A private method to parse event command
     *
     * @param command user's input command
     * @return a command instance that follows the <code>Command</code> interface
     * @throws DukeException if the task description is empty, or the date time format is invalid
     */
    private static Command parseEventCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_EVENT.length()).trim();
        String[] contentAndDate = detail.split(COMMAND_EVENT_SEPARATOR);

        if (detail.length() <= 0 || contentAndDate.length <= 0) {
            throw new DukeException("Invalid format. Enter by this format:\n"
                    + "\t\t\"event [description] /at [date]\"");
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            if (contentAndDate.length != EVENT_DEADLINE_ARGUMENT_COUNT
                    || contentAndDate[i].equals(EMPTY_STRING)) {
                throw new DukeException("Invalid format. Enter by this format:\n"
                        + "\t\t\"event [description] /at [date]\"");
            }
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            contentAndDate[i] = contentAndDate[i].trim();
        }
        try {
            LocalDateTime date = LocalDateTime.parse(contentAndDate[1], dateTimeFormat);
            return new AddCommand(new Event(contentAndDate[0], date, false));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write the date in this format: " + DATE_TIME_FORMAT + '\n'
                    + "\t\te.g: 20 Sep 2021 23:59");
        }
    }

    /**
     * A private method to parse done command
     *
     * @param command user's input command
     * @return a command instance that follows the <code>Command</code> interface
     * @throws DukeException if the number format is invalid
     */
    private static Command parseDoneCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_DONE.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Bro don't you say you've done all that.\n" +
                    "\tPick a number with your done task!");
        }

        try {
            int taskDoneNumber = Integer.parseInt(detail);
            return new DoneCommand(taskDoneNumber);
        } catch (NumberFormatException e) {
            throw new DukeException("Uhm that definitely not a number bro. Pick again.");
        }

    }

    /**
     * A private method to parse help command
     *
     * @param command user's input command
     * @return a command instance that follows the <code>Command</code> interface
     */
    private static Command parseHelpCommand(String command) {
        String detail = command.substring(COMMAND_HELP.length()).trim();

        return new HelpCommand(detail);
    }
}
