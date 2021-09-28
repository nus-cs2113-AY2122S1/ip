package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ClearCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.EchoCommand;
import duke.commands.ExitCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkTaskDoneCommand;
import duke.commands.FindCommand;
import duke.commands.DateCommand;
import duke.storage.Data;
import duke.exceptions.DukeException;

/**
 * Represents a utility class that includes operations that can be performed on a command string input by
 * the user to analyse, find arguments, or create other objects.
 */
public class Parser {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_CLEAR_TASKS = "clear";
    private static final String COMMAND_MARK_TASK_DONE = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_ECHO = "echo";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_DATE = "date";

    private static final String DEADLINE_PREFIX = "/by";
    private static final String EVENT_PREFIX = "/at";
    private static final String DATA_SEPARATOR = "\\|";

    private static String[] trimArrayElements(String[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = inputArray[i].trim();
        }
        return inputArray;
    }

    /**
     * Separates the command word from the string input by the user.
     *
     * @param input input by the user through the user interface
     * @return
     * <p>array of size 2 with the command word in the first entry and the arguments in the second entry.</p>
     * <p>If there are no arguments, then the second entry will be an empty string</p>
     * <p>If the user input is empty, then both entries will be an empty string</p>
     */
    private static String[] separateCommand(String input) {
        String[] commandArgArray = new String[2];
        String[] separatedInput = input.split(" ", 2);
        commandArgArray[0] = separatedInput[0].trim();
        commandArgArray[1] = input.replaceFirst(commandArgArray[0], "").trim();
        return commandArgArray;
    }

    /**
     * Separates the date and time from the arguments to add a {@code Deadline}
     *
     * @param input argument of the user input after command is separated.
     * @return
     * <p>array of size 2 with the description of the {@code Deadline} in the first entry and the date and time
     * in the second entry.</p>
     * <p>If no date and time is provided, then the second entry will be an empty string</p>
     */
    public static String[] separateDeadline(String input) {
        String[] descriptionDateArray = new String[2];
        String[] separatedInput = input.split(DEADLINE_PREFIX, 2);
        descriptionDateArray[0] = separatedInput[0].trim();
        descriptionDateArray[1] =
                input.replaceFirst(descriptionDateArray[0], "")
                        .replaceFirst(DEADLINE_PREFIX, "")
                        .trim();
        return descriptionDateArray;
    }

    /**
     * Separates the date and time from the arguments to add an {@code Event}
     *
     * @param input argument of the user input after command is separated.
     * @return
     * <p>array of size 2 with the description of the {@code Event} in the first entry and the date and time
     * in the second entry.</p>
     * <p>If no date and time is provided, then the second entry will be an empty string</p>
     */
    public static String[] separateEvent(String input) {
        String[] descriptionDateArray = new String[2];
        String[] separatedInput = input.split(EVENT_PREFIX, 2);
        descriptionDateArray[0] = separatedInput[0].trim();
        descriptionDateArray[1] =
                input.replaceFirst(descriptionDateArray[0], "")
                        .replaceFirst(EVENT_PREFIX, "")
                        .trim();
        return descriptionDateArray;
    }

    /**
     * Analyses the {@code userInputString} for its command word and arguments, and tries to match the
     * command word with the commands, and constructs a {@code Command} object for further execution.
     *
     * @param userInputString input from the user through the user interface
     * @return {@code Command}that corresponds to the command word
     * @throws DukeException If the respective {@code Command} object cannot be instantiated
     */
    private static Command parseCommandWithArguments(String userInputString) throws DukeException {

        String[] commandArgArray = separateCommand(userInputString);
        String command = commandArgArray[0].toLowerCase();
        String argument = commandArgArray[1];

        switch (command) {
        case COMMAND_ECHO:
            return new EchoCommand(argument);
        case COMMAND_MARK_TASK_DONE:
            return new MarkTaskDoneCommand(argument);
        case COMMAND_ADD_TODO:
            return new AddToDoCommand(argument);
        case COMMAND_ADD_DEADLINE:
            return new AddDeadlineCommand(argument);
        case COMMAND_ADD_EVENT:
            return new AddEventCommand(argument);
        case COMMAND_DELETE:
            return new DeleteTaskCommand(argument);
        case COMMAND_FIND:
            return new FindCommand(argument);
        case COMMAND_DATE:
            return new DateCommand(argument);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Treats the entire user input string as a command, and to determine which command it matches with, then
     * constructs the respective {@code Command} object for further execution. If the user input string does
     * not match with any command, then it will be further parsed for its command word and arguments.
     *
     * @param userInputString input from the user through the user interface
     * @return {@code Command} that corresponds to the command word
     * @throws DukeException If the respective {@code Command} object cannot be instantiated
     */
    public static Command parseCommand(String userInputString) throws DukeException {

        switch (userInputString.toLowerCase()) {
        case COMMAND_LIST_TASKS:
            return new ListCommand();
        case COMMAND_CLEAR_TASKS:
            return new ClearCommand();
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_EXIT:
            return new ExitCommand();
        default:
            return parseCommandWithArguments(userInputString);
        }
    }

    /**
     * Separates the data string into its important parameters of a {@code Task}, which includes the
     * task type, done status, description, and date and time (if any).
     *
     * @param fileLine data string from storage representing a {@code Task} previously stored
     * @return {@code Data} that represents the {@code Task}
     * @throws DukeException If information from storage is found to be corrupted
     */
    public static Data parseData(String fileLine) throws DukeException {
        String[] parameters = trimArrayElements(fileLine.split(DATA_SEPARATOR));
        return new Data(parameters);
    }
}
