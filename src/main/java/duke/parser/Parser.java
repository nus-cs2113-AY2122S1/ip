package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.AgendaCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkAsDoneCommand;
import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeEmptyTimeException;
import duke.exceptions.DukeInvalidQueryException;
import duke.exceptions.DukeMissingKeywordException;
import duke.exceptions.InvalidCommandException;

/**
 * Parses through the user input to identify and prepare the command to be executed
 */
public class Parser {

    public static final String DEADLINE_BY_PREFIX = "/by";
    public static final String EVENT_AT_PREFIX = "/at";

    /**
     * Parses through and makes sense of the user input to return the correct Command object corresponding
     * to the user command
     *
     * @param input the user input
     * @return Command object corresponding to the user command
     * @throws InvalidCommandException        when command is not recognised
     * @throws NumberFormatException          when the user input does not contain a valid integer as index
     *                                        number of task after done or delete
     * @throws ArrayIndexOutOfBoundsException when the user input does not contain any index number of task
     *                                        after done or delete
     * @throws DukeEmptyDescriptionException  when the description of task is empty
     * @throws DukeEmptyTimeException         when the time-field is empty for event or deadline
     * @throws DukeMissingKeywordException    when there is no "/at" or "/by" when adding a new event or
     *                                        deadline
     */
    public static Command parse(String input) throws InvalidCommandException,
            NumberFormatException,
            ArrayIndexOutOfBoundsException,
            DukeEmptyDescriptionException,
            DukeEmptyTimeException,
            DukeMissingKeywordException,
            DukeInvalidQueryException {
        String command = getFirstWord(input);
        switch (command) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case AgendaCommand.COMMAND_WORD:
            return new AgendaCommand();
        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(input);
        case MarkAsDoneCommand.COMMAND_WORD:
            return prepareMarkAsDoneCommand(input);
        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodoCommand(input);
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineCommand(input);
        case AddEventCommand.COMMAND_WORD:
            return prepareAddEventCommand(input);
        case DeleteTaskCommand.COMMAND_WORD:
            return prepareDeleteTaskCommand(input);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * @param input user input
     * @return MarkAsDoneCommand object which can be executed to mark a task as done
     * @throws NumberFormatException          when the user input does not contain a valid integer as index
     *                                        number of task after done or delete
     * @throws ArrayIndexOutOfBoundsException when the user input does not contain any index number of task
     *                                        after done or delete
     */
    private static MarkAsDoneCommand prepareMarkAsDoneCommand(String input) throws NumberFormatException,
            ArrayIndexOutOfBoundsException {
        int indexOfTaskDone = getIntegerFromCommand(input);
        return new MarkAsDoneCommand(indexOfTaskDone);
    }

    /**
     * @param input user input
     * @return FindCommand object which can be executed to find a print all tasks matching a query
     * @throws DukeInvalidQueryException when there is no query found in user input
     */
    private static FindCommand prepareFindCommand(String input) throws DukeInvalidQueryException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeInvalidQueryException();
        }
        String query = input.replace(splitInput[0], "").trim();
        return new FindCommand(query);
    }

    /**
     * @param input user input
     * @return DeleteTaskCommand object which can be executed to delete a task
     * @throws NumberFormatException          when the user input does not contain a valid integer as index
     *                                        number of task after done or delete
     * @throws ArrayIndexOutOfBoundsException when the user input does not contain any index number of task
     *                                        after done or delete
     */
    private static DeleteTaskCommand prepareDeleteTaskCommand(String input) throws NumberFormatException,
            ArrayIndexOutOfBoundsException {
        int indexOfTaskBeingDeleted = getIntegerFromCommand(input);
        return new DeleteTaskCommand(indexOfTaskBeingDeleted);
    }

    /**
     * @param input user input
     * @return AddTodoCommand object which can be executed to add a todo
     * @throws DukeEmptyDescriptionException when there is no description in the user input
     */
    private static AddTodoCommand prepareAddTodoCommand(String input) throws DukeEmptyDescriptionException {
        String todoInput = removeFirstWordInSentence(input, 4);
        if (todoInput.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        return new AddTodoCommand(todoInput);
    }

    /**
     * @param input user input
     * @return AddDeadlineCommand object which can be executed to add a deadline
     * @throws DukeEmptyDescriptionException when there is no description in the user input
     * @throws DukeEmptyTimeException        when there is no time-field in user input
     * @throws DukeMissingKeywordException   when "/by" cannot be found in user input
     */
    private static AddDeadlineCommand prepareAddDeadlineCommand(String input) throws
            DukeEmptyDescriptionException,
            DukeEmptyTimeException,
            DukeMissingKeywordException {
        String deadlineInput = removeFirstWordInSentence(input, 8);
        final int indexOfByPrefix = getIndexOfByPrefix(deadlineInput);
        String deadlineDescription = getDescription(deadlineInput, indexOfByPrefix);
        String deadlineBy = getTime(deadlineInput, indexOfByPrefix);
        return new AddDeadlineCommand(deadlineDescription, deadlineBy);
    }

    /**
     * @param input user input
     * @return AddEventCommand object which can be executed to add an event
     * @throws DukeEmptyDescriptionException when there is no description in the user input
     * @throws DukeEmptyTimeException        when there is no time-field in user input
     * @throws DukeMissingKeywordException   when "/at" cannot be found in user input
     */
    private static AddEventCommand prepareAddEventCommand(String input) throws
            DukeEmptyDescriptionException,
            DukeEmptyTimeException,
            DukeMissingKeywordException {
        String eventInput = removeFirstWordInSentence(input, 5);
        final int indexOfAtPrefix = getIndexOfAtPrefix(eventInput);
        String eventDescription = getDescription(eventInput, indexOfAtPrefix);
        String eventAt = getTime(eventInput, indexOfAtPrefix);
        return new AddEventCommand(eventDescription, eventAt);
    }

    /**
     * @param input         user input
     * @param indexOfPrefix index of "/at" or "/by" prefix in user input String
     * @return String corresponding to the time-field of the deadline/event
     * @throws DukeEmptyTimeException when no time-field is detected in user input
     */
    private static String getTime(String input, int indexOfPrefix) throws DukeEmptyTimeException {
        String time = input.substring(indexOfPrefix + 3).trim();
        if (time.isEmpty()) {
            throw new DukeEmptyTimeException();
        }
        return time;
    }

    /**
     * @param input         user input
     * @param indexOfPrefix index of "/at" or "/by" prefix in user input String
     * @return String corresponding to the description of the event/deadline
     * @throws DukeEmptyDescriptionException when no description detected in user input
     */
    private static String getDescription(String input, int indexOfPrefix) throws
            DukeEmptyDescriptionException {
        String description = input.substring(0, indexOfPrefix).trim();
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        return description;
    }

    /**
     * @param input user input
     * @return integer corresponding ot the index of "/by" in user input
     * @throws DukeMissingKeywordException when no "/by" is detected in user input
     */
    private static int getIndexOfByPrefix(String input) throws DukeMissingKeywordException {
        int indexOfByPrefix = input.indexOf(DEADLINE_BY_PREFIX);
        if (indexOfByPrefix == -1) {
            throw new DukeMissingKeywordException("/by");
        }
        return indexOfByPrefix;
    }

    /**
     * @param input user input
     * @return integer corresponding to the index of "/at" in user input
     * @throws DukeMissingKeywordException when no "/at" is detected in user input
     */
    private static int getIndexOfAtPrefix(String input) throws DukeMissingKeywordException {
        final int indexOfAtPrefix = input.indexOf(EVENT_AT_PREFIX);
        if (indexOfAtPrefix == -1) {
            throw new DukeMissingKeywordException("/at");
        }
        return indexOfAtPrefix;
    }

    /**
     * @param inputCommand user input
     * @return String corresponding to the first word in the user input
     */
    private static String getFirstWord(String inputCommand) {
        //switch to lowercase so that Duke won't be case-sensitive
        return inputCommand.toLowerCase().split(" ")[0];
    }

    /**
     * @param inputCommand      user input
     * @param lengthOfFirstWord length of the first word in the sentence
     * @return String corresponding to the user input with the first word removed from it
     */
    private static String removeFirstWordInSentence(String inputCommand, int lengthOfFirstWord) {
        //to remove the words "deadline", "even" or "todo"
        return inputCommand.substring(lengthOfFirstWord).trim();
    }

    /**
     * parses the 2nd word in the user input into an integer and returns it
     *
     * @param input user input
     * @return the integer in the 2nd word of the user input corresponding to the index of a task
     * @throws NumberFormatException          when the 2nd word of the user input cannot be parsed into an integer
     * @throws ArrayIndexOutOfBoundsException when the user input does not have a 2nd word
     */
    private static int getIntegerFromCommand(String input) throws NumberFormatException,
            ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split(" ");
        return Integer.parseInt(splitInput[1]);
    }

}
