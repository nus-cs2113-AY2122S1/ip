package duke.parser;

import duke.commands.*;

import duke.data.exception.EmptyTaskException;
import duke.data.exception.InvalidException;

import java.util.Locale;

public class Parser {

    public static final int TASK_DATA_COUNT = 2;
    public static final int TASK_DATA_INDEX_DESCRIPTION = 0;
    public static final int TASK_DATA_INDEX_ADDITIONAL_INFO = 1;

    public static Command parseCommand(String userCommand) throws InvalidException, IndexOutOfBoundsException, EmptyTaskException {
        final String[] commandTypeAndParams = splitUserCommand(userCommand.toLowerCase());
        final String commandType = commandTypeAndParams[TASK_DATA_INDEX_DESCRIPTION];
        final String commandArgs = commandTypeAndParams[TASK_DATA_INDEX_ADDITIONAL_INFO];
        switch (commandType) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DeleteCommand.COMMAND_WORD:
            checkValidArguments(commandArgs);
            return new DeleteCommand(commandArgs);
        case MarkCompleteCommand.COMMAND_WORD:
            checkValidArguments(commandArgs);
            return new MarkCompleteCommand(commandArgs);
        case TodoCommand.COMMAND_WORD:
            checkValidArguments(commandArgs);
            return new TodoCommand(commandArgs);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadlineCommand(commandArgs);
        case EventCommand.COMMAND_WORD:
            return prepareEventCommand(commandArgs);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new InvalidException();
        }
    }

    private static String[] splitUserCommand(String userCommand) {
        final String[] split = userCommand.trim().split(" ", 2);
        if (split.length >= TASK_DATA_COUNT) {
            return split;
        } else {
            return new String[]{split[TASK_DATA_INDEX_DESCRIPTION], ""};
        }
    }

    private static void checkValidArguments(String commandArgs) throws EmptyTaskException {
        if (commandArgs.equals("")) {
            throw new EmptyTaskException();
        }
    }

    public static String[] decodeInput(String rawInput) {
        String[] decoded = new String[TASK_DATA_COUNT];
        String[] splitByForwardSlash = rawInput.split("/", 2);
        decoded[TASK_DATA_INDEX_DESCRIPTION] = splitByForwardSlash[0];
        String[] splitBySpace = splitByForwardSlash[TASK_DATA_INDEX_ADDITIONAL_INFO].split(" ", 2);
        decoded[TASK_DATA_INDEX_ADDITIONAL_INFO] = splitBySpace[1];
        return decoded;
    }

    private static Command prepareDeadlineCommand(String commandArgs) throws EmptyTaskException {
        String[] decodedInput = Parser.decodeInput(commandArgs);
        String description = getDescription(decodedInput);
        String by = getAdditionalInfo(decodedInput);

        if (description.equals("")) {
            throw new EmptyTaskException();
        }
        return new DeadlineCommand(description, by);
    }

    private static Command prepareEventCommand(String commandArgs) throws EmptyTaskException {
        String[] decodedInput = Parser.decodeInput(commandArgs);
        String description = getDescription(decodedInput);
        String at = getAdditionalInfo(decodedInput);

        if (description.equals("")) {
            throw new EmptyTaskException();
        }

        return new EventCommand(description, at);
    }

    public static String getAdditionalInfo(String[] decodedInput) {
        return decodedInput[1];
    }

    public static String getDescription(String[] decodedInput) {
        return decodedInput[0];
    }
}
