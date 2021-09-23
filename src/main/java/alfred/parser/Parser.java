package alfred.parser;

import alfred.command.AddTaskCommand;
import alfred.command.Command;
import alfred.command.CompleteTaskCommand;
import alfred.command.DeleteTaskCommand;
import alfred.command.ExitAppCommand;
import alfred.command.FailedCommand;
import alfred.command.ListTasksCommand;
import alfred.exception.EmptyDescriptionException;
import alfred.exception.InvalidDateException;

public class Parser {
    private final String EXIT_COMMAND = "bye";
    private final String LIST_COMMAND = "list";
    private final String COMPLETE_COMMAND = "done";
    private final String TODO_COMMAND = "todo";
    private final String EVENT_COMMAND = "event";
    private final String DEADLINE_COMMAND = "deadline";
    private final String DELETE_COMMAND = "delete";

    private final String TODO_TYPE = "T";
    private final String EVENT_TYPE = "E";
    private final String DEADLINE_TYPE = "D";
    private final String EMPTY = "";
    private final String AT_IDENTIFIER = " /at ";
    private final String BY_IDENTIFIER = " /by ";

    private final int COMMAND_TYPE_INDEX = 0;
    private final int TASK_COMMAND_INDEX  = 1;
    private final int TASK_FULL_DESCRIPTION_INDEX = 1;

    private final int SPLIT_TASK_DESCRIPTION_INDEX = 0;
    private final int SPLIT_TASK_DATE_INDEX = 1;

    /**
     * This method parses the user's input and deciphers which command should be executed.
     * @param userInput User's input to be parsed
     * @return Command This returns a command to be executed by Alfred
     */
    public Command parseCommand(String userInput) {
        String commandType = getCommandType(userInput);
        Command command;
        switch (commandType) {
        case EXIT_COMMAND:
            command = new ExitAppCommand();
            break;
        case LIST_COMMAND:
            command = new ListTasksCommand();
            break;
        case COMPLETE_COMMAND:
            command = parseCompleteCommand(userInput);
            break;
        case TODO_COMMAND: // Fallthrough
        case EVENT_COMMAND: // Fallthrough
        case DEADLINE_COMMAND:
            command = parseAddTaskCommand(userInput);
            break;
        case DELETE_COMMAND:
            command = parseDeleteCommand(userInput);
            break;
        default:
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    /**
     * This method converts user's index input into the actual TaskList index.
     * @param inputIndex User's index input
     * @return int The actual Task index in TaskList
     */
    private int parseInputIndex(int inputIndex) {
        return inputIndex - 1;
    }

    /**
     * This method destructures the user's input to obtain command type for parsing.
     * @param userInput User's complete input
     * @return String Type of command to be parsed
     */
    private String getCommandType(String userInput) {
        String[] destructuredInputs = userInput.split(" ");
        return destructuredInputs[COMMAND_TYPE_INDEX];
    }

    /**
     * This method destructures the user's input to obtain the Task's index to mark as complete. It will return a
     * CompleteTaskCommand if the given userInput is not erroneous, and will return a FailedCommand if it is.
     * @param userInput User's complete input
     * @return Command Type of command to be executed
     */
    private Command parseCompleteCommand(String userInput) {
        String[] destructuredInputs = userInput.split(" ");
        int taskIndex;
        try {
            String stringIndex = destructuredInputs[TASK_COMMAND_INDEX];
            taskIndex = parseInputIndex(Integer.parseInt(stringIndex));
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.NUMBER_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(FailedCommandType.NO_INDEX_SPECIFIED);
        }
        return new CompleteTaskCommand(taskIndex);
    }

    /**
     * This method destructures the user's input to decipher what Task to add to the TaskList. It will further parse
     * the input based on the Task type, and if input is erroneous, it will return a FailedCommand.
     * @param userInput User's complete input
     * @return Command Type of command to execute for adding Task
     */
    private Command parseAddTaskCommand(String userInput) {
        String commandType = getCommandType(userInput);
        String[] destructuredInputs = userInput.split(" ", 2);
        Command command;
        try {
            switch (commandType) {
            case TODO_COMMAND:
                command = parseTodo(destructuredInputs);
                break;
            case EVENT_COMMAND:
                command = parseEvent(destructuredInputs);
                break;
            case DEADLINE_COMMAND:
                command = parseDeadline(destructuredInputs);
                break;
            default:
                return new FailedCommand(FailedCommandType.GENERAL);
            }
            return command;
        } catch (EmptyDescriptionException e) {
            return new FailedCommand(FailedCommandType.EMPTY_DESCRIPTION);
        } catch (InvalidDateException e) {
            return new FailedCommand(FailedCommandType.INVALID_DATE);
        }
    }

    /**
     * This method parses the User's input into a Todo Task to be used as arguments for the AddTaskCommand.
     * @param inputs Destructured user's inputs
     * @return Command This returns AddTaskCommand
     * @throws EmptyDescriptionException If task description is empty
     */
    private Command parseTodo(String[] inputs) throws EmptyDescriptionException {
        if (inputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String todoDescription = inputs[TASK_FULL_DESCRIPTION_INDEX];
        return new AddTaskCommand(TODO_TYPE, todoDescription, EMPTY);
    }

    /**
     * This method parses the User's input into an Event Task to be used as arguments for the AddTaskCommand.
     * @param inputs Destructured user's inputs
     * @return Command This returns AddTaskCommand
     * @throws EmptyDescriptionException If task description is empty
     * @throws InvalidDateException If date provided is not formatted correctly
     */
    private Command parseEvent(String[] inputs) throws EmptyDescriptionException, InvalidDateException {
        if (inputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] splitTaskDescription = inputs[TASK_FULL_DESCRIPTION_INDEX].split(AT_IDENTIFIER, 2);
        if (splitTaskDescription.length < 2) {
            throw new InvalidDateException();
        }
        String eventDescription = splitTaskDescription[SPLIT_TASK_DESCRIPTION_INDEX];
        String eventDate = splitTaskDescription[SPLIT_TASK_DATE_INDEX];
        return new AddTaskCommand(EVENT_TYPE, eventDescription, eventDate);
    }

    /**
     * This method parses the User's input into a Deadline Task to be used as arguments for the AddTaskCommand.
     * @param inputs Destructured user's inputs
     * @return Command This returns AddTaskCommand
     * @throws EmptyDescriptionException If task description is empty
     * @throws InvalidDateException If date provided is not formatted correctly
     */
    private Command parseDeadline(String[] inputs) throws EmptyDescriptionException, InvalidDateException {
        if (inputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] splitTaskDescription = inputs[TASK_FULL_DESCRIPTION_INDEX].split(BY_IDENTIFIER, 2);
        if (splitTaskDescription.length < 2) {
            throw new InvalidDateException();
        }
        String deadlineDescription = splitTaskDescription[SPLIT_TASK_DESCRIPTION_INDEX];
        String deadlineDate = splitTaskDescription[SPLIT_TASK_DATE_INDEX];
        return new AddTaskCommand(DEADLINE_TYPE, deadlineDescription, deadlineDate);
    }

    /**
     * This method parses the User's input to obtain the Task index for DeleteTaskCommand
     * @param userInput User's complete input
     * @return Command Type of command to be executed
     */
    private Command parseDeleteCommand(String userInput) {
        String[] destructuredInputs = userInput.split(" ");
        int taskIndex;
        try {
            String stringIndex = destructuredInputs[TASK_COMMAND_INDEX];
            taskIndex = parseInputIndex(Integer.parseInt(stringIndex));
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.NUMBER_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(FailedCommandType.NO_INDEX_SPECIFIED);
        }
        return new DeleteTaskCommand(taskIndex);
    }
}
