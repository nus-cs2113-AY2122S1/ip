package duke.components;

import duke.commands.Command;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkDoneCommand;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.IllegalDateTimeException;
import duke.exceptions.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.constants.CommandConstants.COMMAND_EXIT;
import static duke.constants.CommandConstants.COMMAND_FIND;
import static duke.constants.CommandConstants.COMMAND_LIST;
import static duke.constants.CommandConstants.COMMAND_DELETE;
import static duke.constants.CommandConstants.COMMAND_ADD_TODO;
import static duke.constants.CommandConstants.COMMAND_ADD_EVENT;
import static duke.constants.CommandConstants.COMMAND_ADD_DEADLINE;
import static duke.constants.CommandConstants.COMMAND_MARK_DONE;

import static duke.constants.Messages.VERTICAL_BAR;
import static duke.constants.Messages.LINE_SEPARATOR;
import static duke.constants.Messages.EMPTY_STRING;
import static duke.constants.Messages.VERTICAL_BAR_REGEX;
import static duke.constants.TaskConstants.INPUT_DATE_TIME_FORMAT;
import static duke.constants.TaskConstants.OUTPUT_DATE_TIME_FORMAT;
import static duke.constants.TaskConstants.PREFIX_DEADLINE;
import static duke.constants.TaskConstants.PREFIX_EVENT;
import static duke.constants.TaskConstants.PREFIX_TODO;
import static duke.constants.TaskConstants.STATUS_ICON_DONE;

/**
 * A class that deals with making sense of the user command
 * and formatting data to stored and retrieved from the storage.
 */
public class Parser {

    /**
     * Class constructor.
     */
    public Parser() {

    }

    /**
     * Formats a task to be stored in the storage file.
     *
     * @param task The task to be formatted.
     * @return The formatted string representation of task.
     */
    public static String formatTaskToStore(Task task) {
        String prefix;
        String by;
        String time;
        String data;
        String description = task.getDescription();
        String statusIcon = task.getStatusIcon();
        if (task instanceof Deadline) {
            prefix = PREFIX_DEADLINE;
            by = ((Deadline) task).getBy();
            data = prefix + VERTICAL_BAR + statusIcon + VERTICAL_BAR + description + VERTICAL_BAR + by + LINE_SEPARATOR;
        } else if (task instanceof Event) {
            prefix = PREFIX_EVENT;
            time = ((Event) task).getTime();
            data = prefix + VERTICAL_BAR + statusIcon + VERTICAL_BAR + description + VERTICAL_BAR + time + LINE_SEPARATOR;
        } else {
            prefix = PREFIX_TODO;
            data = prefix + VERTICAL_BAR + statusIcon + VERTICAL_BAR + description + LINE_SEPARATOR;
        }
        return data;
    }

    /**
     * Retrieves a task from the storage file.
     *
     * @param data The string representation of the task in the storage file.
     * @return The task to be retrieved.
     * @throws StorageException If there is something wrong with the data file.
     */
    public static Task retrieveStoredData(String data) throws StorageException {
        String[] tokens = data.split(VERTICAL_BAR_REGEX);
        String prefix = tokens[0];
        String statusIcon = tokens[1];
        String description = tokens[2];
        boolean isDone = (statusIcon.equals(STATUS_ICON_DONE)) ? true : false;

        try {
            switch (prefix) {
            case PREFIX_EVENT:
                String time = tokens[3];
                return new Event(description, isDone, time);
            case PREFIX_DEADLINE:
                String by = tokens[3];
                return new Deadline(description, isDone, by);
            case PREFIX_TODO:
                return new ToDo(description, isDone);
            default:
                throw new StorageException();
            }
        } catch (Exception e) {
            throw new StorageException();
        }
    }

    /**
     * Splits raw user input into command word and command arguments.
     *
     * @param userInput Raw input from user.
     * @return a String array of size 2 including the command type and the arguments.
     * @throws EmptyTaskException If the add task command has no task description.
     */
    public static String[] splitCommandAndArgs(String userInput) throws EmptyTaskException {
        String[] tokens = userInput.trim().split("\\s+", 2);
        String command = tokens[0];
        boolean isCommandAdd = command.equals(COMMAND_ADD_DEADLINE) || command.equals(COMMAND_ADD_TODO)
                || command.equals(COMMAND_ADD_EVENT);

        if (tokens.length == 2) {
            return tokens;
        } else if (isCommandAdd) {
            throw new EmptyTaskException();
        } else {
            return new String[] {command, EMPTY_STRING};
        }
    }

    /**
     * Converts date and time in string format to a LocalDateTime object.
     *
     * @param string Date and time in string format.
     * @return A LocalDateTime object that represents the given date and time.
     * @throws IllegalDateTimeException If the input date time is invalid.
     */
    public static LocalDateTime stringToDateTime(String string) throws IllegalDateTimeException {
        try {
            return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT));
        } catch (Exception e) {
            throw new IllegalDateTimeException();
        }
    }

    /**
     * Converts date and time in LocalDateTime format to a string
     *
     * @param dateTime A LocalDateTime object that represents the given date and time.
     * @return The string representation of the given LocalDateTime object.
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT));
    }

    /**
     * Formats the user's input date time.
     *
     * @param rawDateTime The raw date time from user's input.
     * @return The string representation of other form of the given date time.
     * @throws IllegalDateTimeException If the input date time is invalid.
     */
    public static String formatDateTime(String rawDateTime) throws IllegalDateTimeException {
        try {
            return dateTimeToString(stringToDateTime(rawDateTime));
        } catch (IllegalDateTimeException e) {
            throw new IllegalDateTimeException();
        }
    }

    /**
     * Returns a to be executed command based on the raw input from user.
     *
     * @param userInput The raw input from user.
     * @return The command to be executed.
     * @throws DukeException If there is an exception of type DukeException occurs.
     */
    public Command parseCommand(String userInput) throws DukeException {
        final String[] commandTypeAndParams = splitCommandAndArgs(userInput);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];

        switch (commandType) {
        case COMMAND_ADD_TODO:
            return new AddToDoCommand(commandArgs);
        case COMMAND_ADD_DEADLINE:
            return new AddDeadlineCommand(commandArgs);
        case COMMAND_ADD_EVENT:
            return new AddEventCommand(commandArgs);
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK_DONE:
            return new MarkDoneCommand(commandArgs);
        case COMMAND_DELETE:
            return new DeleteCommand(commandArgs);
        case COMMAND_FIND:
            return new FindCommand(commandArgs);
        case COMMAND_EXIT:
            return new ExitCommand();
        default:
            throw new DukeException();
        }
    }

}
