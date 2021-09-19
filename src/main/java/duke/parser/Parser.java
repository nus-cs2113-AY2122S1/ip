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
     * @param input String input by the user through the user interface
     * @return <p><code>String</code> array of size 2 with the command word in the first entry and the
     * arguments in the second entry.</p>
     * <p>If there are no arguments, then the second entry will be an empty <code>String</code></p>
     * <p>If the user input is empty, then both entries will be an empty <code>String</code></p>
     */

    private static String[] separateCommand(String input) {
        String[] commandArgArray = new String[2];
        String[] separatedInput = input.split(" ", 2);
        commandArgArray[0] = separatedInput[0].trim();
        commandArgArray[1] = input.replaceFirst(commandArgArray[0], "").trim();
        return commandArgArray;
    }

    /**
     * Separates the date and time from the arguments to add a <code>Deadline</code>
     *
     * @param input String argument of the user input after command is separated.
     * @return <p><code>String</code> array of size 2 with the description of the <code>Deadline</code> in
     * the first entry and the date and time in the second entry.</p>
     * <p>If no date and time is provided, then the second entry will be an empty <code>String</code></p>
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
     * Separates the date and time from the arguments to add an <code>Event</code>
     *
     * @param input String argument of the user input after command is separated.
     * @return <p><code>String</code> array of size 2 with the description of the <code>Event</code> in
     * the first entry and the date and time in the second entry.</p>
     * <p>If no date and time is provided, then the second entry will be an empty <code>String</code></p>
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
     * Analyses the <code>userInputString</code> for its command word and arguments, and tries to match the
     * command word with the commands, and constructs a <code>Command</code> object for further execution.
     *
     * @param userInputString <code>String</code> input from the user through the user interface
     * @return <code>Command</code> object that corresponds to the command word
     * @throws DukeException If there are no commands that match with the command word
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
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Treats the entire user input string as a command, and to determine which command it matches with, then
     * constructs the respective <code>Command</code> object for further execution.
     *
     * @param userInputString <code>String</code> input from the user through the user interface
     * @return <code>Command</code> object that corresponds to the <code>userInputString</code>
     * @throws DukeException If there are no commands that match with the <code>userInputString</code>
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
     * Separates the data string into its important parameters of a <code>Task</code>, which includes the
     * <code>Task</code> type, done status, description, and date and time (if any).
     *
     * @param fileLine A data string from storage representing a <code>Task</code> previously stored
     * @return <code>Data</code> object that represents the <code>Task</code>
     * @throws DukeException If information of the <code>Task</code> is found to be corrupted
     */

    public static Data parseData(String fileLine) throws DukeException {
        String[] parameters = trimArrayElements(fileLine.split(DATA_SEPARATOR));
        return new Data(parameters);
    }
}
