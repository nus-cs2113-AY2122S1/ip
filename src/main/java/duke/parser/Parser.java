package duke.parser;

import duke.commands.*;
import duke.common.Messages;
import duke.data.exception.EmptyTaskException;
import duke.data.exception.InvalidException;

import static duke.Duke.*;

public class Parser {

    public static Command parseCommand(String userCommand) {
        try {
            final String[] commandTypeAndParams = splitUserCommand(userCommand);
            final String commandType = commandTypeAndParams[0];
            final String commandArgs = commandTypeAndParams[1];
            switch (commandType) {
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
                break;
            case DeleteCommand.COMMAND_WORD:
                checkValidArguments(commandArgs);
                return new DeleteCommand(commandArgs);
                break;
            case MarkCompleteCommand.COMMAND_WORD:
                checkValidArguments(commandArgs);
                executeCompleteTask(commandArgs);
                break;
            case TodoCommand.COMMAND_WORD:
                checkValidArguments(commandArgs);
                executeTodoTask(commandArgs);
                break;
            case DeadlineCommand.COMMAND_WORD:
                checkValidArguments(commandArgs);
                return prepareDeadlineCommand(commandArgs);
            case EventCommand.COMMAND_WORD:
                checkValidArguments(commandArgs);
                executeEventTask(commandArgs);
                break;
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
                break;
            default:
                throw new InvalidException();
            }
        } catch (InvalidException e) {
            System.out.println(Messages.MESSAGE_INVALID_COMMAND);
        } catch (EmptyTaskException e) {
            System.out.println(Messages.MESSAGE_EMPTY_TASK_DESCRIPTION);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Messages.MESSAGE_INVALID_TASK_INDEX);
        }
    }

    private static String[] splitUserCommand(String userCommand) {
        final String[] split = userCommand.trim().split(" ", 2);
        if (split.length >= 2) {
            return split;
        } else {
            return new String[]{split[0], ""};
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
        String[] splitBySpace = splitByForwardSlash[1].split(" ", 2);
        decoded[TASK_DATA_INDEX_ADDITIONAL_INFO] = splitBySpace[1];
        return decoded;
    }

    private static Command prepareDeadlineCommand(String commandArgs) {
        String[] decodedInput = Parser.decodeInput(commandArgs);
        String description = getDescription(decodedInput);
        String by = getAdditionalInfo(decodedInput);

        return new DeadlineCommand(description,by);
    }

    public static String getAdditionalInfo(String[] decodedInput) {
        return decodedInput[1];
    }

    public static String getDescription(String[] decodedInput) {
        return decodedInput[0];
    }
}
