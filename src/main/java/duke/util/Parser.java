package duke.util;

import duke.DukeException;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    static private final String COMMAND_LIST = "list";
    static private final String COMMAND_TODO = "todo";
    static private final String COMMAND_EVENT = "event";
    static private final String COMMAND_DEADLINE = "deadline";
    static private final String COMMAND_DONE = "done";
    static private final String COMMAND_DELETE = "delete";
    static private final String COMMAND_FIND = "find";
    static private final String COMMAND_EXIT = "bye";

    static private final String INVALID_COMMAND = "Yo check your typing man. I don't get it.";

    static private final String COMMAND_DEADLINE_SEPARATOR = "/by";
    static private final String COMMAND_EVENT_SEPARATOR = "/at";

    static private final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm";
    static private final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    static private final String SPACE_SEPARATOR = " ";
    static private final String EMPTY_STRING = "";

    static private final int EVENT_DEADLINE_ARGUMENT_COUNT = 2;


    public static boolean isExit(String command) {
        return (command.equals(COMMAND_EXIT));
    }

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
        default:
            throw new DukeException(INVALID_COMMAND);
        }
    }

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

    private static Command parseFindCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_FIND.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Nothing to find!");
        }
        return new FindCommand(detail);
    }

    private static Command parseExitCommand() {
        return new ExitCommand();
    }

    private static Command parseListCommand() {
        return new ListCommand();
    }

    private static Command parseTodoCommand(String command) throws DukeException {
        String detail = command.substring(COMMAND_TODO.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Bro please let me know what thing you gonna do");
        }

        return new AddCommand(new ToDos(detail, false));
    }

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
}
