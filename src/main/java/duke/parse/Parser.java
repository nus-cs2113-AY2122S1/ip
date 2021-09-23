package duke.parse;

import duke.DukeException;
import duke.commands.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Makes sense of user inputs
 */
public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String INVALID_COMMAND = "invalid command";
    private static final int COMMAND_INDEX = 0;

    /**
     * Identify the command that users input
     * @param line the user input
     * @return the corresponding Command to execute
     * @throws DukeException when an invalid command is detected
     */
    public static Command parse(String line) throws DukeException {
        String[] words = line.split(" ");

        switch (words[COMMAND_INDEX]) {
        case (COMMAND_LIST):
            return parseListCommand(line);
        case (COMMAND_TODO):
            return parseTodoCommand(line);
        case (COMMAND_DEADLINE):
            return parseDeadlineCommand(line, words);
        case (COMMAND_EVENT):
            return parseEventCommand(line, words);
        case (COMMAND_DONE):
            return parseDoneCommand(line, words);
        case (COMMAND_DELETE):
            return parseDeleteCommand(line, words);
        case (COMMAND_BYE):
            return parseByeCommand(line, words);
        default:
            throw new DukeException(INVALID_COMMAND);
        }
    }

    /**
     * Checks for errors in list command input before returning the Command
     * @param line the user input
     * @return Command that executes list
     * @throws DukeException when list is input in incorrect format
     */
    public static Command parseListCommand(String line) throws DukeException {
        final String LIST_ERROR = "list does not take in additional parameters";

        if (!line.equals(COMMAND_LIST)) {
            throw new DukeException(LIST_ERROR);
        }

        return new ListCommand();
    }

    /**
     * Checks for errors in todo command input before returning the Command
     * @param line the user input
     * @return Command that adds todo task
     * @throws DukeException when todo command has missing parameters
     */
    public static Command parseTodoCommand(String line) throws DukeException {
        final String TODO_ERROR = "todo description missing";
        final int START_INDEX = 5;

        if (line.equals(COMMAND_TODO)) {
            throw new DukeException(TODO_ERROR);
        }

        return new AddCommand(new Todo(line.substring(START_INDEX)));
    }

    /**
     * Checks for errors in deadline command input before returning the Command
     * @param line the user input
     * @param words list of words from user input split by spacing
     * @return Command to add deadline task
     * @throws DukeException when there are errors in user input
     */
    public static Command parseDeadlineCommand(String line, String[] words) throws DukeException {
        final String BY_DELIMITER = "/by";
        final String DEADLINE_ERROR_1 = "specify task and date/time";
        final String DEADLINE_ERROR_2 = "deadlines need to contain \"/by\"";
        final String DEADLINE_ERROR_3 = "deadline description missing";
        final String DEADLINE_ERROR_4 = "date/time missing";
        final int DESCRIPTION_INDEX_ERROR = 1;
        final int DESCRIPTION_INDEX = 0;
        final int BY_INDEX = 1;
        final int START_INDEX = 9;

        if (line.equals(COMMAND_DEADLINE)) {
            throw new DukeException(DEADLINE_ERROR_1);
        }

        if (!line.contains(BY_DELIMITER)) {
            throw new DukeException(DEADLINE_ERROR_2);
        }

        if (words[DESCRIPTION_INDEX_ERROR].equals(BY_DELIMITER)) {
            throw new DukeException(DEADLINE_ERROR_3);
        }

        if (line.endsWith(BY_DELIMITER)) {
            throw new DukeException(DEADLINE_ERROR_4);
        }

        String[] deadlineInputs = line.substring(START_INDEX).split(BY_DELIMITER);

        return new AddCommand(new Deadline(deadlineInputs[DESCRIPTION_INDEX].trim(),
                deadlineInputs[BY_INDEX].trim()));
    }

    /**
     * Checks for error in event command input before returning Command
     * @param line the user input
     * @param words list of words from user input split by spacing
     * @return Command to add event task
     * @throws DukeException when there are errors in user input
     */
    public static Command parseEventCommand(String line, String[] words) throws DukeException {
        final String AT_DELIMITER = "/at";
        final String EVENT_ERROR_1 = "specify task and date/time";
        final String EVENT_ERROR_2 = "events need to contain \"/at\"";
        final String EVENT_ERROR_3 = "event description missing";
        final String EVENT_ERROR_4 = "date/time missing";
        final int EVENT_INDEX_ERROR = 1;
        final int EVENT_INDEX = 0;
        final int AT_INDEX = 1;
        final int START_INDEX = 6;

        if (line.equals(COMMAND_EVENT)) {
            throw new DukeException(EVENT_ERROR_1);
        }

        if (!line.contains(AT_DELIMITER)) {
            throw new DukeException(EVENT_ERROR_2);
        }

        if (words[EVENT_INDEX_ERROR].equals(AT_DELIMITER)) {
            throw new DukeException(EVENT_ERROR_3);
        }

        if (line.endsWith(AT_DELIMITER)) {
            throw new DukeException(EVENT_ERROR_4);
        }

        String[] eventInputs = line.substring(START_INDEX).split(AT_DELIMITER);

        return new AddCommand(new Event(eventInputs[EVENT_INDEX].trim(),
                eventInputs[AT_INDEX].trim()));
    }

    /**
     * Checks for errors in done command input before returning Command
     * @param line the user input
     * @param words the list of words from user input split by space
     * @return Command to mark task done by index
     * @throws DukeException when there are errors in user input
     */
    public static Command parseDoneCommand(String line, String[] words) throws DukeException {
        final String DONE_ERROR_1 = "missing index of task done";
        final String DONE_ERROR_2 = "extra parameters found";
        final String DONE_ERROR_3 = "non-integer value for index of task done";
        final int MAX_LENGTH = 2;
        final int INDEX_DONE = 1;
        int index;

        if (line.equals(COMMAND_DONE)) {
            throw new DukeException(DONE_ERROR_1);
        }

        if (words.length > MAX_LENGTH) {
            throw new DukeException(DONE_ERROR_2);
        }

        try {
            index = Integer.parseInt(words[INDEX_DONE]);
        } catch (NumberFormatException e) {
            throw new DukeException(DONE_ERROR_3);
        }

        return new DoneCommand(index);
    }

    /**
     * Checks for errors in delete command input before returning Command
     * @param line the user input
     * @param words the list of words from user input split by space
     * @return Command to delete task by index
     * @throws DukeException when there are errors in user input
     */
    public static Command parseDeleteCommand(String line, String[] words) throws DukeException {
        final String DELETE_ERROR_1 = "missing index of task to delete";
        final String DELETE_ERROR_2 = "extra parameters found";
        final String DELETE_ERROR_3 = "non-integer value for index of task to delete";
        final int MAX_LENGTH = 2;
        final int INDEX_DELETE = 1;
        int index;

        if (line.equals(COMMAND_DELETE)) {
            throw new DukeException(DELETE_ERROR_1);
        }

        if (words.length > MAX_LENGTH) {
            throw new DukeException(DELETE_ERROR_2);
        }

        try {
            index = Integer.parseInt(words[INDEX_DELETE]);
        } catch (NumberFormatException e) {
            throw new DukeException(DELETE_ERROR_3);
        }

        return new DeleteCommand(index);
    }

    /**
     * Checks for errors in bye command user input before returning Command
     * @param line the user input
     * @param words this list of words from user input split by space
     * @return Command to save before exit program
     * @throws DukeException when there are additional parameters for bye
     */
    public static Command parseByeCommand(String line, String[] words) throws DukeException {
        final String BYE_ERROR = "bye does not take in additional parameters";

        if (!line.equals(COMMAND_BYE)) {
            throw new DukeException(BYE_ERROR);
        }

        return new ExitCommand();
    }
}
