package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_MARK_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_NEW_TODO = "todo";
    private static final String COMMAND_NEW_DEADLINE = "deadline";
    private static final String COMMAND_NEW_EVENT = "event";
    private static final String COMMAND_FIND = "find";

    private static final Character SPACE_CHARACTER = ' ';
    private static final String EMPTY_STRING = "";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_SEPARATOR = " /at ";

    private static final String ERROR_INVALID_COMMAND = "Sorry, I do not understand your command.";
    private static final String ERROR_DONE_INVALID_NUMBER = "Please provide a valid number.";
    private static final String ERROR_DONE_MISSING_NUMBER = "Please specify the index of a task.";
    private static final String ERROR_DELETE_INVALID_NUMBER = "Please provide a valid number.";
    private static final String ERROR_DELETE_MISSING_NUMBER = "Please specify the index of a task.";
    private static final String ERROR_FIND_INVALID_FORMAT = "Please enter your search in the format \"find [keyword]\".";
    private static final String ERROR_FIND_MISSING_KEYWORD = "Please specify a keyword to search for.";
    private static final String ERROR_TODO_INVALID_FORMAT = "Please provide a todo in the format \"todo [task name]\".";
    private static final String ERROR_TODO_MISSING_NAME = "Please specify a todo name.";
    private static final String ERROR_DEADLINE_INVALID_FORMAT = "Please provide a deadline in the format \"deadline [task name] /by [date + time]\".";
    private static final String ERROR_DEADLINE_MISSING_SEPARATOR = "Please use the \" /by \" separator to separate the deadline name and date + time.";
    private static final String ERROR_DEADLINE_MISSING_NAME = "Please specify a deadline name.";
    private static final String ERROR_DEADLINE_MISSING_DATE_TIME = "Please specify a do by date and time.";
    private static final String ERROR_DATE_TIME_INVALID_FORMAT = "Please provide the date in the format \"dd mm yyyy HHMM\", eg. \"12 02 2021 1830\".";
    private static final String ERROR_EVENT_INVALID_FORMAT = "Please provide an in the format \"event [task name] /at [date]\".";
    private static final String ERROR_EVENT_MISSING_SEPARATOR = "Please use the \" /at \" separator to separate the event name and date.";
    private static final String ERROR_EVENT_MISSING_NAME = "Please specify an event name.";
    private static final String ERROR_EVENT_MISSING_DATE = "Please specify an event date.";

    private static final String DATE_TIME_FORMAT_PATTERN = "dd MM yyyy HHmm";

    /**
     * Takes in the userInput String and returns the corresponding command to be executed
     *
     * @param userInput The String that contains the user's input
     * @return The corresponding Command based on the user's input
     * @throws DukeException If the userInput String is not in the correct format or
     *                       does not correspond to a valid command
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals(COMMAND_EXIT)) {
            return new ExitCommand();
        }
        if (userInput.equals(COMMAND_LIST)) {
            return new ListCommand();
        }
        if (userInput.equals(COMMAND_HELP)) {
            return new HelpCommand();
        }
        if (userInput.startsWith(COMMAND_MARK_DONE)) {
            return parseDoneCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_DELETE)) {
            return parseDeleteCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_FIND)) {
            return parseFindCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_NEW_TODO)) {
            Task task = parseTodoTask(userInput);
            return new AddCommand(task);
        }
        if (userInput.startsWith(COMMAND_NEW_DEADLINE)) {
            Task task = parseDeadlineTask(userInput);
            return new AddCommand(task);
        }
        if (userInput.startsWith(COMMAND_NEW_EVENT)) {
            Task task = parseEventTask(userInput);
            return new AddCommand(task);
        }
        throw new DukeException(ERROR_INVALID_COMMAND);
    }

    /**
     * Takes in a user input String corresponding to a DoneCommand and returns a DoneCommand
     * containing the specified information
     *
     * @param userInput String corresponding to a DoneCommand
     * @return a DoneCommand containing the information specified in userInput
     * @throws DukeException If the userInput String does not contain a valid task index
     */
    private static DoneCommand parseDoneCommand(String userInput) throws DukeException {
        int taskIndex = -1;
        try {
            taskIndex = Integer.parseInt(userInput.substring(5).strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_DONE_INVALID_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_DONE_MISSING_NUMBER);
        }
        return new DoneCommand(taskIndex);
    }

    /**
     * Takes in a user input String corresponding to a DeleteCommand and returns a DeleteCommand
     * containing the specified information
     *
     * @param userInput String corresponding to a DeleteCommand
     * @return a DeleteCommand containing the information specified in userInput
     * @throws DukeException If the userInput String does not contain a valid task index
     */
    private static DeleteCommand parseDeleteCommand(String userInput) throws DukeException {
        int taskIndex = -1;
        try {
            taskIndex = Integer.parseInt(userInput.substring(7).strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_DELETE_INVALID_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_DELETE_MISSING_NUMBER);
        }
        return new DeleteCommand(taskIndex);
    }

    /**
     * Takes in a user input String corresponding to a FindCommand and returns a FindCommand
     * containing the specified information
     *
     * @param userInput String corresponding to a FindCommand
     * @return a FindCommand containing the information specified in userInput
     * @throws DukeException If the userInput String does not contain a keyword to search for
     */
    private static FindCommand parseFindCommand(String userInput) throws DukeException {
        String keyword = "";
        try {
            //if no space character after "find"
            if (userInput.charAt(4) != SPACE_CHARACTER) {
                throw new DukeException(ERROR_FIND_INVALID_FORMAT);
            }
            keyword = userInput.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_FIND_MISSING_KEYWORD);
        }
        return new FindCommand(keyword);
    }

    /**
     * Takes in the userInput String containing information for a ToDo and returns
     * a ToDo containing the specified information
     *
     * @param userInput the user input String that corresponds to a command to add
     *                  a ToDo
     * @return an Event that contains the details specified in userInput
     * @throws DukeException if the userInput String is not in the correct format
     *                       or has missing information
     */
    private static ToDo parseTodoTask(String userInput) throws DukeException {
        try {
            //if no space character after "todo"
            if (userInput.charAt(4) != SPACE_CHARACTER) {
                throw new DukeException(ERROR_TODO_INVALID_FORMAT);
            }
            //if task name is not found
            String todoName = userInput.substring(4).strip();
            if (todoName.equals(EMPTY_STRING)) {
                throw new DukeException(ERROR_TODO_MISSING_NAME);
            }
            return new ToDo(todoName);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_TODO_INVALID_FORMAT);
        }
    }

    /**
     * Takes in the userInput String containing information for a Deadline and returns
     * a Deadline containing the specified information
     *
     * @param userInput the user input String that corresponds to a command to add
     *                  a Deadline
     * @return an Event that contains the details specified in userInput
     * @throws DukeException if the userInput String is not in the correct format
     *                       or has missing information
     */
    private static Deadline parseDeadlineTask(String userInput) throws DukeException {
        try {
            //if no space character after "deadline"
            if (userInput.charAt(8) != SPACE_CHARACTER) {
                throw new DukeException(ERROR_DEADLINE_INVALID_FORMAT);
            }
            int byIndex = userInput.indexOf(DEADLINE_SEPARATOR);
            //if " /by " separator is not found
            if (byIndex == -1) {
                throw new DukeException(ERROR_DEADLINE_MISSING_SEPARATOR);
            }
            String deadlineName = userInput.substring(8, byIndex).strip();
            //if task name is found
            if (deadlineName.equals(EMPTY_STRING)) {
                throw new DukeException(ERROR_DEADLINE_MISSING_NAME);
            }
            String dateTimeString = userInput.substring(byIndex + 5).strip();
            //if do by date is not found
            if (dateTimeString.equals(EMPTY_STRING)) {
                throw new DukeException(ERROR_DEADLINE_MISSING_DATE_TIME);
            }
            LocalDateTime deadlineDateTime = parseDateTime(dateTimeString);
            return new Deadline(deadlineName, deadlineDateTime);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_DEADLINE_INVALID_FORMAT);
        }
    }

    /**
     * Takes in a String containing Date and Time information and returns a LocalDateTime
     * with that information.
     *
     * @param dateTimeString String containing Date and Time information
     * @return LocalDateTime corresponding to the information in dateTimeString
     * @throws DukeException if the dateTimeString is in the incorrect format
     */
    private static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormat);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException(ERROR_DATE_TIME_INVALID_FORMAT);
        }
    }

    /**
     * Takes in the userInput String containing information for an Event and returns
     * an Event containing the specified information
     *
     * @param userInput the user input String that corresponds to a command to add
     *                  an Event
     * @return an Event that contains the details specified in userInput
     * @throws DukeException if the userInput String is not in the correct format
     *                       or has missing information
     */
    private static Event parseEventTask(String userInput) throws DukeException {
        try {
            //if no space character after "event"
            if (userInput.charAt(5) != SPACE_CHARACTER) {
                throw new DukeException(ERROR_EVENT_INVALID_FORMAT);
            }
            int atIndex = userInput.indexOf(EVENT_SEPARATOR);
            //if " /at " separator is not found
            if (atIndex == -1) {
                throw new DukeException(ERROR_EVENT_MISSING_SEPARATOR);
            }
            String eventName = userInput.substring(5, atIndex).strip();
            //if task name is not found
            if (eventName.equals(EMPTY_STRING)) {
                throw new DukeException(ERROR_EVENT_MISSING_NAME);
            }
            String eventDate = userInput.substring(atIndex + 5).strip();
            //if event date is not found
            if (eventDate.equals(EMPTY_STRING)) {
                throw new DukeException(ERROR_EVENT_MISSING_DATE);
            }
            return new Event(eventName, eventDate);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_EVENT_INVALID_FORMAT);
        }
    }
}
