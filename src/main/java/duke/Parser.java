package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.exception.EmptyDescriptionException;
import duke.task.exception.EmptySearchTermException;
import duke.task.exception.EmptyTimeDetailException;
import duke.task.exception.TimeSpecifierNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {

    protected static final String COMMAND_HELP = "help";
    protected static final String COMMAND_LIST = "list";
    protected static final String COMMAND_DONE = "done";
    protected static final String COMMAND_BYE = "bye";
    protected static final String COMMAND_TODO = "todo";
    protected static final String COMMAND_DEADLINE = "deadline";
    protected static final String COMMAND_EVENT = "event";
    protected static final String COMMAND_DELETE = "delete";
    protected static final String COMMAND_FIND = "find";
    protected static final String TIME_SPECIFIER_BY = "/by";
    protected static final String TIME_SPECIFIER_AT = "/at";

    public Parser() {
    }

    /**
     * Parse the user command.
     *
     * @param fullCommand Full user command.
     * @return Appropriate Command.
     * @throws NumberFormatException          Task index provided in command is not a number.
     * @throws UnknownCommandException        Unknown user command provided.
     * @throws EmptyDescriptionException      Task description is empty.
     * @throws EmptyTimeDetailException       Time detail is not provided in the user command.
     * @throws TimeSpecifierNotFoundException Time specifier is not provided in the user command.
     * @throws DateTimeParseException         Date provided is not in ISO8601 format.
     * @throws EmptySearchTermException       Search term provided is empty.
     */
    public Command parse(String fullCommand)
            throws NumberFormatException, UnknownCommandException, EmptyDescriptionException, EmptyTimeDetailException,
            TimeSpecifierNotFoundException, DateTimeParseException, EmptySearchTermException {
        String[] separatedCommand = fullCommand.split(" ");
        String userCommand = separatedCommand[0];

        Command command;
        switch (userCommand) {
        case COMMAND_LIST:
            command = new ListCommand();
            break;
        case COMMAND_DONE:
            int doneTaskIndex = Integer.parseInt(separatedCommand[1]) - 1;
            command = new DoneCommand(doneTaskIndex);
            break;
        case COMMAND_TODO:
            command = prepareAddTodo(separatedCommand);
            break;
        case COMMAND_DEADLINE:
            command = prepareAddDeadline(separatedCommand);
            break;
        case COMMAND_EVENT:
            command = prepareAddEvent(separatedCommand);
            break;
        case COMMAND_DELETE:
            int deleteTaskIndex = Integer.parseInt(separatedCommand[1]) - 1;
            command = new DeleteCommand(deleteTaskIndex);
            break;
        case COMMAND_FIND:
            String searchTerm = extractSearchTerm(separatedCommand);
            command = new FindCommand(searchTerm);
            break;
        case COMMAND_HELP:
            command = new HelpCommand();
            break;
        case COMMAND_BYE:
            command = new ByeCommand();
            break;
        default:
            throw new UnknownCommandException();
        }

        return command;
    }

    /**
     * Check if time detail index is found and valid.
     *
     * @param timeSpecifierIndex Index of time specifier.
     * @return If timeSpecifierIndex is not -1.
     */
    private boolean isTimeSpecifierFound(int timeSpecifierIndex) {
        return timeSpecifierIndex != -1;
    }

    /**
     * Get the index of the time specifier "/by" or "/at".
     *
     * @param separatedCommand String array of each word in user input.
     * @param timeSpecifier    Time specifier TIME_SPECIFIER_BY or TIME_SPECIFIER_AT.
     * @return Index of time specifier in the separated user command.
     * @throws TimeSpecifierNotFoundException Time specifier "/by" or "/at" is not found in user input.
     */
    private int getTimeSpecifierIndex(String[] separatedCommand, String timeSpecifier)
            throws TimeSpecifierNotFoundException {
        int timeSpecifierIndex = Arrays.asList(separatedCommand).indexOf(timeSpecifier);

        if (!isTimeSpecifierFound(timeSpecifierIndex)) {
            throw new TimeSpecifierNotFoundException();
        }

        return timeSpecifierIndex;
    }

    /**
     * Get the index of "/by" specifier in the separated user command.
     *
     * @param separatedCommand String array of each word in user input.
     * @return Index of "/by" specifier in the separated user command.
     * @throws TimeSpecifierNotFoundException "/by" specifier is not found in user input.
     */
    private int getByIndex(String[] separatedCommand) throws TimeSpecifierNotFoundException {
        return getTimeSpecifierIndex(separatedCommand, TIME_SPECIFIER_BY);
    }

    /**
     * Get the index of "/at" specifier in the separated user command.
     *
     * @param separatedCommand String array of each word in user input.
     * @return Index of "/at" specifier in the separated user command.
     * @throws TimeSpecifierNotFoundException "/at" specifier is not found in user input.
     */
    private int getAtIndex(String[] separatedCommand) throws TimeSpecifierNotFoundException {
        return getTimeSpecifierIndex(separatedCommand, TIME_SPECIFIER_AT);
    }

    /**
     * Extract time detail from user input.
     *
     * @param separatedCommand String array of each word in user input.
     * @param startIndex       Starting index of time detail in user input array.
     * @return LocalDate object of time detail.
     * @throws EmptyTimeDetailException Time detail not found in user input.
     * @throws DateTimeParseException   Incorrect date format of time detail.
     */
    private static LocalDate extractTimeDetail(String[] separatedCommand, int startIndex)
            throws EmptyTimeDetailException, DateTimeParseException {
        String timeDetailString = String.join(" ",
                Arrays.copyOfRange(separatedCommand, startIndex, separatedCommand.length));

        if (timeDetailString.isBlank()) {
            throw new EmptyTimeDetailException();
        }

        return LocalDate.parse(timeDetailString);
    }

    /**
     * Extract task description from user input.
     *
     * @param separatedCommand String array of each word in user input.
     * @param endIndex         End index of task description in user input array.
     * @return Extracted task description.
     * @throws EmptyDescriptionException Task description is not provided.
     */
    private String extractDescription(String[] separatedCommand, int endIndex) throws EmptyDescriptionException {
        String description = String.join(" ", Arrays.copyOfRange(separatedCommand, 1, endIndex));

        if (description.isBlank()) {
            throw new EmptyDescriptionException();
        }

        return description;
    }

    /**
     * Prepare a new add command for Todo task.
     *
     * @param separatedCommand String array of each word in user input.
     * @return AddCommand for Todo task.
     * @throws EmptyDescriptionException Task description is empty.
     */
    private AddCommand prepareAddTodo(String[] separatedCommand) throws EmptyDescriptionException {
        String description = extractDescription(separatedCommand, separatedCommand.length);
        return new AddCommand(new Todo(description));
    }

    /**
     * Prepare a new add command for Deadline task.
     *
     * @param separatedCommand String array of each word in user input.
     * @return AddCommand for Deadline task.
     * @throws EmptyDescriptionException      Description not provided in command.
     * @throws TimeSpecifierNotFoundException Time specifier not provided in command.
     * @throws EmptyTimeDetailException       Time detail not provided in command.
     * @throws DateTimeParseException         Date provided is not in ISO8601 format.
     */
    private AddCommand prepareAddDeadline(String[] separatedCommand)
            throws TimeSpecifierNotFoundException, EmptyDescriptionException, EmptyTimeDetailException,
            DateTimeParseException {
        int byIndex = getByIndex(separatedCommand);
        String description = extractDescription(separatedCommand, byIndex);
        LocalDate by = extractTimeDetail(separatedCommand, byIndex + 1);

        return new AddCommand(new Deadline(description, by));
    }

    /**
     * Prepare a new add command for Event task.
     *
     * @param separatedCommand String array of each word in user input.
     * @return AddCommand for Event task.
     * @throws EmptyDescriptionException      Description not provided in command.
     * @throws TimeSpecifierNotFoundException Time specifier not provided in command.
     * @throws EmptyTimeDetailException       Time detail not provided in command.
     * @throws DateTimeParseException         Date provided is not in ISO8601 format.
     */
    private AddCommand prepareAddEvent(String[] separatedCommand)
            throws TimeSpecifierNotFoundException, EmptyDescriptionException, EmptyTimeDetailException,
            DateTimeParseException {
        int atIndex = getAtIndex(separatedCommand);
        String description = extractDescription(separatedCommand, atIndex);
        LocalDate at = extractTimeDetail(separatedCommand, atIndex + 1);

        return new AddCommand(new Event(description, at));
    }

    /**
     * Extract search term from user command.
     *
     * @param separatedCommand String array of each word in user input.
     * @return String search term.
     * @throws EmptySearchTermException No search term was provided.
     */
    private String extractSearchTerm(String[] separatedCommand) throws EmptySearchTermException {
        if (separatedCommand.length == 1) {
            throw new EmptySearchTermException();
        }

        String[] searchTerm = Arrays.copyOfRange(separatedCommand, 1, separatedCommand.length);
        return String.join(" ", searchTerm);
    }
}
