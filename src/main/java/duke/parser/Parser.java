package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.AgendaCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.MarkAsDoneCommand;
import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeEmptyTimeException;
import duke.exceptions.DukeMissingKeywordException;
import duke.exceptions.InvalidCommandException;

public class Parser {
    
    public static final String DEADLINE_BY_PREFIX = "/by";
    public static final String EVENT_AT_PREFIX = "/at";
    
    public static Command parse(String input) throws InvalidCommandException,
            NumberFormatException,
            ArrayIndexOutOfBoundsException,
            DukeEmptyDescriptionException,
            DukeEmptyTimeException,
            DukeMissingKeywordException {
        String command = getFirstWord(input);
        switch (command) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case AgendaCommand.COMMAND_WORD:
            return new AgendaCommand();
        case MarkAsDoneCommand.COMMAND_WORD:
            return prepareMarkAsDoneCommand(input);
        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodoCommand(input);
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineCommand(input);
        case AddEventCommand.COMMAND_WORD:
            return prepareAddEventCommand(input);
        case DeleteTaskCommand.COMMAND_WORD:
            return prepareDeleteTaskCommand(input);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new InvalidCommandException();
        }
    }
    
    private static MarkAsDoneCommand prepareMarkAsDoneCommand(String input) throws NumberFormatException,
            ArrayIndexOutOfBoundsException {
        int indexOfTaskDone = getIntegerFromCommand(input);
        return new MarkAsDoneCommand(indexOfTaskDone);
    }
    
    private static DeleteTaskCommand prepareDeleteTaskCommand(String input) throws NumberFormatException,
            ArrayIndexOutOfBoundsException {
        int indexOfTaskBeingDeleted = getIntegerFromCommand(input);
        return new DeleteTaskCommand(indexOfTaskBeingDeleted);
    }
    
    private static AddTodoCommand prepareAddTodoCommand(String input) throws DukeEmptyDescriptionException {
        String todoInput = removeFirstWordInSentence(input, 4);
        if (todoInput.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        return new AddTodoCommand(todoInput);
    }
    
    private static AddDeadlineCommand prepareAddDeadlineCommand(String input) throws 
            DukeEmptyDescriptionException,
            DukeEmptyTimeException,
            DukeMissingKeywordException {
        String deadlineInput = removeFirstWordInSentence(input, 8);
        final int indexOfByPrefix = getIndexOfByPrefix(deadlineInput);
        String deadlineDescription = getDescription(deadlineInput, indexOfByPrefix);
        String deadlineBy = getTime(deadlineInput, indexOfByPrefix);
        return new AddDeadlineCommand(deadlineDescription,deadlineBy);
    }
    
    private static AddEventCommand prepareAddEventCommand(String input) throws
            DukeEmptyDescriptionException,
            DukeEmptyTimeException,
            DukeMissingKeywordException {
        String eventInput = removeFirstWordInSentence(input, 5);
        final int indexOfAtPrefix = getIndexOfAtPrefix(eventInput);
        String eventDescription = getDescription(eventInput,indexOfAtPrefix);
        String eventAt = getTime(eventInput, indexOfAtPrefix);
        return new AddEventCommand(eventDescription, eventAt);
    }

    private static String getTime(String input, int indexOfPrefix) throws DukeEmptyTimeException {
        String time = input.substring(indexOfPrefix + 3).trim();
        if (time.isEmpty()) {
            throw new DukeEmptyTimeException();
        }
        return time;
    }

    private static String getDescription(String input, int indexOfPrefix) throws
            DukeEmptyDescriptionException {
        String description = input.substring(0, indexOfPrefix).trim();
        if (description.isEmpty()) {
            throw new DukeEmptyDescriptionException();
        }
        return description;
    }

    private static int getIndexOfByPrefix(String input) throws DukeMissingKeywordException {
        int indexOfByPrefix = input.indexOf(DEADLINE_BY_PREFIX);
        if (indexOfByPrefix == -1) {
            throw new DukeMissingKeywordException("/by");
        }
        return indexOfByPrefix;
    }

    private static int getIndexOfAtPrefix(String input) throws DukeMissingKeywordException {
        final int indexOfAtPrefix = input.indexOf(EVENT_AT_PREFIX);
        if (indexOfAtPrefix == -1) {
            throw new DukeMissingKeywordException("/at");
        }
        return indexOfAtPrefix;
    }

    private static String getFirstWord(String inputCommand) {
        //switch to lowercase so that Duke won't be case-sensitive
        return inputCommand.toLowerCase().split(" ")[0];
    }

    private static String removeFirstWordInSentence(String inputCommand, int lengthOfFirstWord) {
        //to remove the words "deadline", "even" or "todo"
        return inputCommand.substring(lengthOfFirstWord).trim();
    }

    private static int getIntegerFromCommand(String input) throws NumberFormatException,
            ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split(" ");
        return Integer.parseInt(splitInput[1]);
    }
    
}
