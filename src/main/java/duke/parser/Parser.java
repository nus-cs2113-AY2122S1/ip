package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.UnrecognizedCommand;
import duke.exception.InvalidCommandFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.constants.DukeCommandStrings.DATE_TIME_INPUT_FORMAT;
import static duke.constants.DukeCommandStrings.DEADLINE_COMMAND;
import static duke.constants.DukeCommandStrings.DEADLINE_PREFIX;
import static duke.constants.DukeCommandStrings.DELETE_COMMAND;
import static duke.constants.DukeCommandStrings.DONE_COMMAND;
import static duke.constants.DukeCommandStrings.EVENT_COMMAND;
import static duke.constants.DukeCommandStrings.EVENT_PREFIX;
import static duke.constants.DukeCommandStrings.EXIT_COMMAND;
import static duke.constants.DukeCommandStrings.FIND_COMMAND;
import static duke.constants.DukeCommandStrings.HELP_COMMAND;
import static duke.constants.DukeCommandStrings.LIST_COMMAND;
import static duke.constants.DukeCommandStrings.TODO_COMMAND;
import static duke.constants.DukeCommandStrings.WHITESPACE_SEQUENCE;

/**
 * Parses user input.
 */
public class Parser {
    
    /**
     * Parses {@code userInput} into a subclass of {@code Command} for execution.
     * 
     * @param userInput full user input string
     * @return {@code Command} object
     */
    public static Command parseCommandWord(String userInput) throws InvalidCommandFormatException, NumberFormatException, DateTimeParseException {
        if (beginsWith(userInput, LIST_COMMAND)) {
            return new ListCommand();
        } else if (beginsWith(userInput, DONE_COMMAND)) {
            int indexOfTaskToMarkDone = parseDoneCommand(userInput);
            return new DoneCommand(indexOfTaskToMarkDone);
        } else if (beginsWith(userInput, TODO_COMMAND)) {
            Todo newTodo = parseAddTodoCommand(userInput);
            return new AddTodoCommand(newTodo);
        } else if (beginsWith(userInput, DEADLINE_COMMAND)) {
            Deadline newDeadline = parseAddDeadlineCommand(userInput);
            return new AddDeadlineCommand(newDeadline);
        } else if (beginsWith(userInput, EVENT_COMMAND)) {
            Event newEvent = parseAddEventCommand(userInput);
            return new AddEventCommand(newEvent);
        } else if (beginsWith(userInput, DELETE_COMMAND)) {
            int indexOfTaskToDelete = parseDeleteCommand(userInput);
            return new DeleteCommand(indexOfTaskToDelete);
        } else if (beginsWith(userInput, HELP_COMMAND)) {
            return new HelpCommand();
        } else if (beginsWith(userInput, FIND_COMMAND)) {
            String keyword = parseFindCommand(userInput);
            return new FindCommand(keyword);
        } else if (beginsWith(userInput, EXIT_COMMAND)) {
            return new ByeCommand();
        } else {
            return new UnrecognizedCommand();
        }
    }

    /**
     * Parses {@code userInput} based on the format of the {@code done} command and returns the task ID of the task to
     * mark done.
     *
     * @param userInput full user input string
     * @return task ID of the task to mark done
     * @throws InvalidCommandFormatException if task ID is non-numeric, lacking from command
     * @throws NumberFormatException if task ID is not in the task list
     */
    private static int parseDoneCommand(String userInput) throws InvalidCommandFormatException, NumberFormatException {
        String[] doneCommandAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(doneCommandAsArray.length != 2) {
            /* throws exception because task ID is lacking */
            throw new InvalidCommandFormatException();
        }

        int indexOfTaskToMarkDone;
        try {
            /* '- 1' to convert from 1-based to 0-based indexing */
            indexOfTaskToMarkDone = Integer.parseInt(doneCommandAsArray[1]) - 1;
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
        return indexOfTaskToMarkDone;
    }

    /**
     * Parses {@code userInput} based on the format of the {@code todo} command and returns a {@code Todo} object
     * with a description as entered by the user.
     *
     * @param userInput full user input string
     * @return a {@code Todo} with the description as entered by the user
     * @throws InvalidCommandFormatException if the todo is lacking a description
     */
    private static Todo parseAddTodoCommand(String userInput) throws InvalidCommandFormatException {
        String[] todoAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(todoAsArray.length != 2) {
            /* throws exception because description is lacking */
            throw new InvalidCommandFormatException();
        }
        String todoDescription = todoAsArray[1].trim();
        return new Todo(todoDescription);
    }

    /**
     * Parses {@code userInput} based on the format of the {@code deadline} command and returns a {@code Deadline} object
     * with description and deadline as entered by the user.
     *
     * @param userInput full user input string
     * @return a {@code Deadline} with a description and deadline as entered by the user
     * @throws InvalidCommandFormatException if the deadline is lacking either a description or a date and time
     * @throws DateTimeParseException if the date and time do not follow the correct format or if an invalid date
     *                                and time is entered
     */
    private static Deadline parseAddDeadlineCommand(String userInput) throws InvalidCommandFormatException, DateTimeParseException {
        String[] deadlineAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(deadlineAsArray.length != 2) {
            /* throws exception because both description and deadline are lacking */
            throw new InvalidCommandFormatException();
        }

        String[] deadlineDescriptionAndBy = deadlineAsArray[1].split(DEADLINE_PREFIX, 2);
        if (deadlineDescriptionAndBy.length != 2) {
            /* throws exception because either description or deadline is lacking */
            throw new InvalidCommandFormatException();
        }

        String deadlineDescription = deadlineDescriptionAndBy[0].trim();
        String deadlineBy = deadlineDescriptionAndBy[1].trim();
        boolean isInvalidDeadline = deadlineDescription.isEmpty() || deadlineBy.isEmpty();
        if (isInvalidDeadline) {
            throw new InvalidCommandFormatException();
        }
        LocalDateTime deadlineDateTime = parseDateTime(deadlineBy, DATE_TIME_INPUT_FORMAT);
        return new Deadline(deadlineDescription, deadlineDateTime);
    }

    /**
     * Parses {@code userInput} based on the format of the {@code event} command and returns an {@code Event} object
     * with description and event time as entered by the user.
     *
     * @param userInput full user input string
     * @return an {@code Event} with a description and event time as entered by the user
     * @throws InvalidCommandFormatException if the event is lacking either a description or a date and time
     * @throws DateTimeParseException if the date and time do not follow the correct format or if an invalid date
     *                                and time is entered
     */
    private static Event parseAddEventCommand(String userInput) throws InvalidCommandFormatException, DateTimeParseException {
        String[] eventAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(eventAsArray.length != 2) {
            /* throws exception because both description and event time are lacking */
            throw new InvalidCommandFormatException();
        }

        String[] eventDescriptionAndWhen = eventAsArray[1].split(EVENT_PREFIX, 2);
        if(eventDescriptionAndWhen.length != 2) {
            /* throws exception because either description or event time are lacking */
            throw new InvalidCommandFormatException();
        }

        String eventDescription = eventDescriptionAndWhen[0].trim();
        String eventWhen = eventDescriptionAndWhen[1].trim();
        boolean isInvalidEvent = eventDescription.isEmpty() || eventWhen.isEmpty();
        if (isInvalidEvent) {
            throw new InvalidCommandFormatException();
        }
        LocalDateTime eventDateTime = parseDateTime(eventWhen, DATE_TIME_INPUT_FORMAT);
        return new Event(eventDescription, eventDateTime);
    }

    /**
     * Parses {@code userInput} based on the format of the {@code delete} command and returns the task ID of the task to
     * delete.
     *
     * @param userInput full user input string
     * @return task ID of the task to delete
     * @throws InvalidCommandFormatException if task ID is non-numeric, lacking from command
     * @throws NumberFormatException if task ID is not in the task list
     */
    private static int parseDeleteCommand(String userInput) throws InvalidCommandFormatException, NumberFormatException {
        String[] deleteCommandAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(deleteCommandAsArray.length != 2) {
            /* throws exception because task ID is lacking */
            throw new InvalidCommandFormatException();
        }

        int indexOfTaskToDelete;
        try {
            /* '- 1' to convert from 1-based to 0-based indexing */
            indexOfTaskToDelete = Integer.parseInt(deleteCommandAsArray[1]) - 1;
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
        return indexOfTaskToDelete;
    }

    /**
     * Parses {@code userInput} based on the format of the {@code find} command and returns the keyword to search for
     * as a {@code String}.
     *
     * @param userInput full user input string
     * @return a {@code String} representing the keyword to search for
     * @throws InvalidCommandFormatException if the keyword is lacking from the user input
     */
    private static String parseFindCommand(String userInput) throws InvalidCommandFormatException {
        String[] findCommandAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if (findCommandAsArray.length != 2) {
            /* throws exception because keyword is lacking */
            throw new InvalidCommandFormatException();
        }
        return findCommandAsArray[1].trim();
    }

    /**
     * Parses a {@code String} representing a date and time based on a format specified by {@code format}.
     * 
     * @param taskDateTime a {@code String} representing the date and time entered by the user
     * @param format date and time format to follow when parsing {@code taskDateTime}
     * @return a {@code LocalDateTime} object which represents the date and time shown by {@code taskDateTime}
     * @throws DateTimeParseException if {@code taskDateTime} does not follow format specified by {@code format}
     */
    public static LocalDateTime parseDateTime(String taskDateTime, String format) throws DateTimeParseException {
        DateTimeFormatter formatToParse = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(taskDateTime, formatToParse);
    }

    /**
     * Converts a {@code LocalDateTime} object to a {@code String} with a format specified by {@code format}.
     * 
     * @param taskDateTime {@code LocalDateTime} object to be formatted
     * @param format date and time format to follow when converting {@code taskDateTime} to {@code String}
     * @return formatted {@code String} which represents {@code taskDateTime}
     */
    public static String dateTimeToString(LocalDateTime taskDateTime, String format) {
        DateTimeFormatter formatToConvertTo = DateTimeFormatter.ofPattern(format);
        return taskDateTime.format(formatToConvertTo);
    }

    /**
     * Helper function to improve readability of {@code parseCommandWord} method.
     */
    private static boolean beginsWith(String userInput, String command) {
        return userInput.trim().toLowerCase().startsWith(command);
    }
}
