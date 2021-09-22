package duke.startup;

import Type.Deadline;
import Type.Event;
import Type.Task;
import Type.Todo;
import duke.command.*;

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


    public static Command parse(String fullCommand) {
        String stringToRead = fullCommand.trim().toLowerCase();
        if (stringToRead.startsWith("add")) {
            return new AddCommand();
        } else if (stringToRead.startsWith("bye")) {
            return new ByeCommand();
        } else if (stringToRead.startsWith("clear")) {
            return new ClearCommand();
        } else if (stringToRead.startsWith("delete")) {
            return new DeleteCommand();
        } else if (stringToRead.startsWith("done")) {
            return new DoneCommand();
        } else if (stringToRead.startsWith("echo")) {
            return new EchoCommand();
        } else if (stringToRead.startsWith("list")) {
            return new ListCommand();
        } else if (stringToRead.startsWith("mascot")) {
            return new MascotCommand();
        } else if (stringToRead.startsWith("find")) {
            return new FindCommand();
        } else if (stringToRead.startsWith("date")) {
            return new DateCommand();
        } else {
            Ui.printWrongCommand();
            return new OopsieCommand();
        }
    }
}
