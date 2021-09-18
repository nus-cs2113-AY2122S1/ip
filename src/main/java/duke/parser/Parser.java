package duke.parser;

import duke.command.CommandWord;
import duke.exception.InvalidCommandFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import static duke.constants.DukeCommandStrings.*;

public class Parser {

    public static CommandWord parseCommandWord(String userInput) {
        if (beginsWith(userInput, LIST_COMMAND)) {
            return CommandWord.LIST;
        } else if (beginsWith(userInput, DONE_COMMAND)) {
            return CommandWord.DONE;
        } else if (beginsWith(userInput, TODO_COMMAND)) {
            return CommandWord.TODO;
        } else if (beginsWith(userInput, DEADLINE_COMMAND)) {
            return CommandWord.DEADLINE;
        } else if (beginsWith(userInput, EVENT_COMMAND)) {
            return CommandWord.EVENT;
        } else if (beginsWith(userInput, DELETE_COMMAND)) {
            return CommandWord.DELETE;
        } else {
            return CommandWord.INVALID;
        }
    }

    public static String[] parseDoneCommand(String userInput) throws InvalidCommandFormatException {
        String[] doneCommandAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(doneCommandAsArray.length != 2) {
            throw new InvalidCommandFormatException();
        }
        return doneCommandAsArray;
    }

    public static Todo parseAddTodoCommand(String userInput) throws InvalidCommandFormatException {
        String[] todoAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(todoAsArray.length != 2) {
            throw new InvalidCommandFormatException();
        }
        String todoDescription = todoAsArray[1].trim();
        return new Todo(todoDescription);
    }

    public static Deadline parseAddDeadlineCommand(String userInput) throws InvalidCommandFormatException {
        String[] deadlineAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(deadlineAsArray.length != 2) {
            throw new InvalidCommandFormatException();
        }

        String[] deadlineDescriptionAndBy = deadlineAsArray[1].split(DEADLINE_PREFIX, 2);
        if (deadlineDescriptionAndBy.length != 2) {
            throw new InvalidCommandFormatException();
        }

        String deadlineDescription = deadlineDescriptionAndBy[0].trim();
        String deadlineBy = deadlineDescriptionAndBy[1].trim();
        boolean isInvalidDeadline = deadlineDescription.isEmpty() || deadlineBy.isEmpty();
        if (isInvalidDeadline) {
            throw new InvalidCommandFormatException();
        }
        return new Deadline(deadlineDescription, deadlineBy);
    }

    public static Event parseAddEventCommand(String userInput) throws InvalidCommandFormatException {
        String[] eventAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(eventAsArray.length != 2) {
            throw new InvalidCommandFormatException();
        }

        String[] eventDescriptionAndWhen = eventAsArray[1].split(EVENT_PREFIX, 2);
        if(eventDescriptionAndWhen.length != 2) {
            throw new InvalidCommandFormatException();
        }

        String eventDescription = eventDescriptionAndWhen[0].trim();
        String eventWhen = eventDescriptionAndWhen[1].trim();
        boolean isInvalidEvent = eventDescription.isEmpty() || eventWhen.isEmpty();
        if (isInvalidEvent) {
            throw new InvalidCommandFormatException();
        }
        return new Event(eventDescription, eventWhen);
    }

    public static String[] parseDeleteCommand(String userInput) throws InvalidCommandFormatException {
        String[] deleteCommandAsArray = userInput.split(WHITESPACE_SEQUENCE, 2);
        if(deleteCommandAsArray.length != 2) {
            throw new InvalidCommandFormatException();
        }
        return deleteCommandAsArray;
    }

    private static boolean beginsWith(String userInput, String command) {
        return userInput.trim().toLowerCase().startsWith(command);
    }
}
