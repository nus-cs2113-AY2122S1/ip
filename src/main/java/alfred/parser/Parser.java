package alfred.parser;

import alfred.command.AddTaskCommand;
import alfred.command.Command;
import alfred.command.CompleteTaskCommand;
import alfred.command.DeleteTaskCommand;
import alfred.command.ExitAppCommand;
import alfred.command.FailedCommand;
import alfred.command.FindCommand;
import alfred.command.ListTasksCommand;
import alfred.exception.EmptyDescriptionException;
import alfred.exception.MissingDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private final String EXIT_COMMAND = "bye";
    private final String LIST_COMMAND = "list";
    private final String COMPLETE_COMMAND = "done";
    private final String TODO_COMMAND = "todo";
    private final String EVENT_COMMAND = "event";
    private final String DEADLINE_COMMAND = "deadline";
    private final String DELETE_COMMAND = "delete";
    private final String FIND_COMMAND = "find";

    private final String TODO_TYPE = "T";
    private final String EVENT_TYPE = "E";
    private final String DEADLINE_TYPE = "D";
    private final LocalDate EMPTY_DATE = null;
    public static final DateTimeFormatter SG_DATE_FORMAT =
            DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy][ddMMyyyy]");
    private final String AT_IDENTIFIER = " /at ";
    private final String BY_IDENTIFIER = " /by ";

    private final int COMMAND_TYPE_INDEX = 0;
    private final int TASK_COMMAND_INDEX  = 1;
    private final int TASK_FULL_DESCRIPTION_INDEX = 1;

    private final int SPLIT_TASK_DESCRIPTION_INDEX = 0;
    private final int SPLIT_TASK_DATE_INDEX = 1;

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
        case FIND_COMMAND:
            command = parseFindCommand(userInput);
            break;
        default:
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    private int parseInputIndex(int inputIndex) {
        return inputIndex - 1;
    }

    private String getCommandType(String userInput) {
        String[] destructuredInputs = userInput.split(" ");
        return destructuredInputs[COMMAND_TYPE_INDEX];
    }

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
        } catch (MissingDateException e) {
            return new FailedCommand(FailedCommandType.MISSING_DATE);
        } catch (DateTimeParseException e) {
            return new FailedCommand(FailedCommandType.INVALID_DATE);
        }
    }

    private Command parseTodo(String[] inputs) throws EmptyDescriptionException {
        if (inputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String todoDescription = inputs[TASK_FULL_DESCRIPTION_INDEX];
        return new AddTaskCommand(TODO_TYPE, todoDescription, EMPTY_DATE);
    }

    private Command parseEvent(String[] inputs) throws EmptyDescriptionException, MissingDateException,
            DateTimeParseException {

        if (inputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] splitTaskDescription = inputs[TASK_FULL_DESCRIPTION_INDEX].split(AT_IDENTIFIER, 2);
        if (splitTaskDescription.length < 2) {
            throw new MissingDateException();
        }
        String eventDescription = splitTaskDescription[SPLIT_TASK_DESCRIPTION_INDEX];
        String eventDateString = splitTaskDescription[SPLIT_TASK_DATE_INDEX];
        LocalDate eventDate = LocalDate.parse(eventDateString, SG_DATE_FORMAT);
        return new AddTaskCommand(EVENT_TYPE, eventDescription, eventDate);
    }

    private Command parseDeadline(String[] inputs) throws EmptyDescriptionException, MissingDateException,
            DateTimeParseException {

        if (inputs.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] splitTaskDescription = inputs[TASK_FULL_DESCRIPTION_INDEX].split(BY_IDENTIFIER, 2);
        if (splitTaskDescription.length < 2) {
            throw new MissingDateException();
        }
        String deadlineDescription = splitTaskDescription[SPLIT_TASK_DESCRIPTION_INDEX];
        String deadlineDateString = splitTaskDescription[SPLIT_TASK_DATE_INDEX];
        LocalDate deadlineDate = LocalDate.parse(deadlineDateString, SG_DATE_FORMAT);
        return new AddTaskCommand(DEADLINE_TYPE, deadlineDescription, deadlineDate);
    }

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

    private Command parseFindCommand(String userInput) {
        String[] destructuredInputs = userInput.split(" ", 2);
        String query;
        try {
            query = destructuredInputs[TASK_FULL_DESCRIPTION_INDEX];
        } catch (IndexOutOfBoundsException e) {
            return new FailedCommand(FailedCommandType.NO_QUERY_SPECIFIED);
        }
        return new FindCommand(query);
    }
}
