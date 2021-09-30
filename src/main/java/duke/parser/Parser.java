package duke.parser;

import duke.commands.*;

import duke.data.exception.EmptyTaskException;
import duke.data.exception.InvalidException;

/**
 * Parses user input.
 */
public class Parser {

    public static final int TASK_DATA_COUNT = 2;
    public static final int TASK_DATA_INDEX_DESCRIPTION = 0;
    public static final int TASK_DATA_INDEX_ADDITIONAL_INFO = 1;

    /**
     * Parses user input into a proper command for execution.
     *
     * @param userCommand a String that includes command and additional info
     * @return a Command that is executable by the program
     * @throws InvalidException          if the user command does not exist
     * @throws IndexOutOfBoundsException if the provided index for some commands are out of the task list range
     * @throws EmptyTaskException        if the user did not provide additional information for some commands
     */
    public static Command parseCommand(String userCommand) throws InvalidException, IndexOutOfBoundsException, EmptyTaskException {
        final String[] commandTypeAndParams = splitUserCommand(userCommand.toLowerCase());
        final String commandType = commandTypeAndParams[TASK_DATA_INDEX_DESCRIPTION].trim();
        final String commandArgs = commandTypeAndParams[TASK_DATA_INDEX_ADDITIONAL_INFO].trim();
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
        case FindCommand.COMMAND_WORD:
            checkValidArguments(commandArgs);
            return new FindCommand(commandArgs);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new InvalidException();
        }
    }

    /**
     * Splits user input into the command type and its arguments.
     *
     * @param userCommand the raw user input
     * @return an array where the first index is the command type, and the second index is the command arguments.
     * If no arguments found, an empty string will be in the second index instead.
     */
    private static String[] splitUserCommand(String userCommand) {
        final String[] split = userCommand.trim().split(" ", 2);
        if (split.length >= TASK_DATA_COUNT) {
            return split;
        } else {
            return new String[]{split[TASK_DATA_INDEX_DESCRIPTION], ""};
        }
    }

    /**
     * Checks whether command arguments exist.
     * This method will only be used for commands that require command arguments.
     *
     * @param commandArgs represents the task description or additional info such as deadline/date
     * @throws EmptyTaskException if no task description is found
     */
    private static void checkValidArguments(String commandArgs) throws EmptyTaskException {
        if (commandArgs.equals("")) {
            throw new EmptyTaskException();
        }
    }

    /**
     * Returns a Deadline command with arguments in the correct format.
     *
     * @param commandArgs raw input for command arguments
     * @return a deadline command with the task's description and deadline as parameters
     * @throws EmptyTaskException if the task description or deadline is empty
     */
    private static Command prepareDeadlineCommand(String commandArgs) throws EmptyTaskException {
        String[] decodedInput = Parser.decodeInput(commandArgs, DeadlineCommand.COMMAND_WORD);
        String description = getDescription(decodedInput);
        String by = getAdditionalInfo(decodedInput);

        if (description.equals("") || (by.equals(""))) {
            throw new EmptyTaskException();
        }
        return new DeadlineCommand(description, by);
    }

    /**
     * Returns an Event command with arguments in the correct format.
     *
     * @param commandArgs raw input for command arguments
     * @return an event command with the task's description and event date/time as parameters
     * @throws EmptyTaskException if the task description or event date/time is empty
     */
    private static Command prepareEventCommand(String commandArgs) throws EmptyTaskException {
        String[] decodedInput = Parser.decodeInput(commandArgs, EventCommand.COMMAND_WORD);
        String description = getDescription(decodedInput);
        String at = getAdditionalInfo(decodedInput);

        if (description.equals("") || at.equals("")) {
            throw new EmptyTaskException();
        }

        return new EventCommand(description, at);
    }

    /**
     * Splits the provided command arguments returns it in a proper format.
     *
     * @param rawInput the command arguments for a deadline/event task
     * @return an array where the first index is the task description and second index is additional info (deadline/ event datetime)
     * If either of them does not exist, return an empty array instead.
     */
    public static String[] decodeInput(String rawInput, String taskType) {
        try {
            String[] decoded = new String[TASK_DATA_COUNT];
            String[] splitBySlash = splitByTaskType(rawInput, taskType);
            decoded[TASK_DATA_INDEX_DESCRIPTION] = splitBySlash[0];
            String[] splitBySpace = splitBySlash[TASK_DATA_INDEX_ADDITIONAL_INFO].split(" ", 2);
            decoded[TASK_DATA_INDEX_ADDITIONAL_INFO] = splitBySpace[1];
            return decoded;
        } catch (IndexOutOfBoundsException e) {
            return new String[]{"", ""};
        }
    }

    /**
     * Splits the command arguments based on its command type.
     *
     * @param rawInput the command arguments
     * @param taskType a string that represents the type of task involved
     * @return an array that contains the split elements (description and deadline/event date) of the input
     */
    private static String[] splitByTaskType(String rawInput, String taskType) {
        String[] splitByForwardSlash;
        switch(taskType) {
        case DeadlineCommand.COMMAND_WORD:
            splitByForwardSlash = rawInput.split(DeadlineCommand.COMMAND_SPLITTER, 2);
            break;
        case EventCommand.COMMAND_WORD:
            splitByForwardSlash = rawInput.split(EventCommand.COMMAND_SPLITTER, 2);
            break;
        default:
            splitByForwardSlash = new String[]{"",""};
        }
        return splitByForwardSlash;
    }

    /**
     * Returns the deadline/ event datetime of the task.
     *
     * @param decodedInput array that includes task description and additional info
     */
    public static String getAdditionalInfo(String[] decodedInput) {
        return decodedInput[1].trim();
    }

    /**
     * Returns the task description.
     *
     * @param decodedInput array that includes task description and additional info
     */
    public static String getDescription(String[] decodedInput) {
        return decodedInput[0].trim();
    }
}