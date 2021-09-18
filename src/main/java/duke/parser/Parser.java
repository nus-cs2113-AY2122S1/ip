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
import duke.storage.Data;
import duke.exceptions.DukeException;

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

    private static final String DEADLINE_PREFIX = "/by";
    private static final String EVENT_PREFIX = "/at";
    private static final String DATA_SEPARATOR = "\\|";

    private static String[] trimArrayElements(String[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = inputArray[i].trim();
        }
        return inputArray;
    }

    private static String[] separateCommand(String input) {
        String[] commandArgArray = new String[2];
        String[] separatedInput = input.split(" ", 2);
        commandArgArray[0] = separatedInput[0].trim();
        commandArgArray[1] = input.replaceFirst(commandArgArray[0], "").trim();
        return commandArgArray;
    }

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
        default:
            return new InvalidCommand();
        }
    }

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

    public static Data parseData(String fileLine) throws DukeException {
        String[] parameters = trimArrayElements(fileLine.split(DATA_SEPARATOR));
        return new Data(parameters);
    }
}
