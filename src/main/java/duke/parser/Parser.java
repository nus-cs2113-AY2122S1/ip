package duke.parser;

import static duke.command.CommandType.valueOf;
import static duke.ui.CommandMessage.HELP_MESSAGE;
import static duke.ui.ErrorMessage.DEADLINE_ERROR_MESSAGE;
import static duke.ui.ErrorMessage.EMPTY_DESCRIPTION_ERROR_MESSAGE;
import static duke.ui.ErrorMessage.EVENT_ERROR_MESSAGE;
import static duke.ui.ErrorMessage.UNKNOWN_TASK_TYPE_ERROR_MESSAGE;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.parser.exception.ParserException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;
import java.util.Locale;

public class Parser {

    /* User input is seperated by an empty space */
    public static final String USER_INPUT_SEPERATOR = " ";

    /* Types of tasks */
    public static final char CHAR_TYPE_TODO = 'T';
    public static final char CHAR_TYPE_DEADLINE = 'D';
    public static final char CHAR_TYPE_EVENT = 'E';

    /* Types of string split regex  */
    public static final String EVENT_STRING_SPLIT_REGEX = "/at ";
    public static final String DEADLINE_STRING_SPLIT_REGEX = "/by ";
    public static final String DATA_TEXT_SEPERATOR = ",";

    /**
     * Parses full command
     *
     * @param fullCommand Command given by user that should be parsed
     * @return Command object based on the parsed command
     * @throws DukeException Catch invalid commands exception and throw as DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] userInputArray = fullCommand.split(USER_INPUT_SEPERATOR, 2);
        String userCommand = userInputArray[0].toUpperCase(Locale.ROOT);
        try {
            Command command;
            CommandType commandType = valueOf(userCommand);
            Task task;
            switch (commandType) {
            case LIST:
                command = new ListCommand();
                break;
            case BYE:
                command = new ByeCommand();
                break;
            case DEADLINE:
                task = parseUserInputTaskString(userInputArray[1], TaskType.DEADLINE);
                command = new AddCommand(task);
                break;
            case EVENT:
                task = parseUserInputTaskString(userInputArray[1], TaskType.EVENT);
                command = new AddCommand(task);
                break;
            case TODO:
                task = parseUserInputTaskString(userInputArray[1], TaskType.TODO);
                command = new AddCommand(task);
                break;
            case DONE:
                command = new DoneCommand(Integer.parseInt(userInputArray[1]));
                break;
            case DELETE:
                command = new DeleteCommand(Integer.parseInt(userInputArray[1]));
                break;
            case HELP:
                command = new HelpCommand();
                break;
            case FIND:
                command = new FindCommand(userInputArray[1]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandType);
            }

            return command;
        } catch (NumberFormatException e) {
            throw new DukeException("Please give a number for the following command: " + userCommand + " <number>");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid Command Given.\n" + HELP_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "Please give command in the following format, you are missing something.\n" + HELP_MESSAGE);
        }
    }

    /**
     * Parses formatted line into task
     *
     * @param formattedTaskString Task formatted as String with ',' as delimiter
     * @return Task object
     * @throws DukeException Catch invalid task type exception and throw as DukeException
     */
    public static Task parseFormattedTaskString(String formattedTaskString) throws DukeException {
        try {
            String[] userInputArray = formattedTaskString.split(DATA_TEXT_SEPERATOR);
            boolean isDone = Integer.parseInt(userInputArray[1]) == 1;
            Task task;
            switch (userInputArray[0].charAt(0)) {
            case CHAR_TYPE_TODO:
                task = new Todo(userInputArray[2]);
                task.setIsDone(isDone);
                return task;
            case CHAR_TYPE_DEADLINE:
                task = new Deadline(userInputArray[2], userInputArray[3]);
                task.setIsDone(isDone);
                return task;
            case CHAR_TYPE_EVENT:
                task = new Event(userInputArray[2], userInputArray[3]);
                task.setIsDone(isDone);
                return task;
            default:
                throw new ParserException(UNKNOWN_TASK_TYPE_ERROR_MESSAGE);
            }
        } catch (ParserException e) {
            throw new DukeException(e.getMessage());
        }

    }

    /**
     * Parses user input into task
     *
     * @param userInput User input for task description
     * @return Task object
     * @throws DukeException Catch invalid task type exception and throw as DukeException
     */
    public static Task parseUserInputTaskString(String userInput, TaskType taskType) throws DukeException {
        try {
            boolean emptyDescription = userInput.isBlank();
            boolean regexNotFound = false;

            if (emptyDescription) {
                throw new ParserException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
            Task task = null;
            String[] userArguments = null;
            switch (taskType) {
            case TODO:
                task = new Todo(userInput);
                break;
            case DEADLINE:
                userArguments = userInput.split(DEADLINE_STRING_SPLIT_REGEX, 2);
                regexNotFound = userArguments.length != 2;
                emptyDescription = userArguments[0].isBlank();
                if (emptyDescription || regexNotFound) {
                    throw new ParserException(DEADLINE_ERROR_MESSAGE);
                }
                task = new Deadline(userArguments[0], userArguments[1]);
                break;
            case EVENT:
                userArguments = userInput.split(EVENT_STRING_SPLIT_REGEX, 2);
                regexNotFound = userArguments.length != 2;
                emptyDescription = userArguments[0].isBlank();
                if (emptyDescription || regexNotFound) {
                    throw new ParserException(EVENT_ERROR_MESSAGE);
                }
                task = new Event(userArguments[0], userArguments[1]);
                break;
            default:
                throw new ParserException(UNKNOWN_TASK_TYPE_ERROR_MESSAGE);
            }
            return task;
        } catch (ParserException e) {
            throw new DukeException(e.getMessage());
        }

    }
}
