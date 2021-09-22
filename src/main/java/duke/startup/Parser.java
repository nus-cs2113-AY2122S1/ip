package duke.startup;

import Type.Deadline;
import Type.Event;
import Type.Task;
import Type.Todo;
import duke.exception.InputCheckAndPrint;

public class Parser {
    public static final String EVENT_DIVIDER = "/at";
    public static final String DEADLINE_DIVIDER = "/by";

    public static Task parseInputAsTask(String userInput) {
        if (inputIsEvent(userInput)) {
            Event eventToAdd = parseInputToEvent(userInput);
            return eventToAdd;
        } else if (inputIsDeadline(userInput)) {
            Deadline deadlineToAdd = parseInputToDeadline(userInput);
            return deadlineToAdd;
        } else {
            Todo todoToAdd = new Todo(userInput);
            return todoToAdd;
        }
    }
    protected static String[] splitInput(String userInput, String divider) {
        return userInput.split(divider);
    }

    protected static Event parseInputToEvent(String userInput) {
        String[] eventData = splitInput(userInput, EVENT_DIVIDER);
        return new Event(eventData[0], eventData[1]);
    }

    protected static Deadline parseInputToDeadline(String userInput) {
        String[] deadlineData = splitInput(userInput, DEADLINE_DIVIDER);
        return new Deadline(deadlineData[0], deadlineData[1]);
    }

    protected static boolean inputIsDeadline(String userInput) {
        return userInput.toLowerCase().contains(DEADLINE_DIVIDER);
    }

    protected static boolean inputIsEvent(String userInput) {
        return userInput.toLowerCase().contains(EVENT_DIVIDER);
    }

    private static boolean isIncorrectFormat(InputCheckAndPrint deadlineCheck, String input) {
        if (input.startsWith("stop")) {
            return true;
        }
        if (!input.contains("/")) {
            deadlineCheck.printDeadlineFormatIssue();
            return true;
        }
        return false;
    }
}
