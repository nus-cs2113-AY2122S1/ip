package duke.startup;

import duke.Type.Deadline;
import duke.Type.Event;
import duke.Type.Task;
import duke.Type.Todo;
import duke.Type.Divider;
import duke.command.*;

public class Parser {
    /**
     * Converts string to Task
     * @param userInput user input as string
     * @return TaskToAdd task object with respective attributes
     */
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
        String[] eventData = splitInput(userInput, Divider.E.getDivisor());
        return new Event(eventData[0], eventData[1]);
    }

    protected static Deadline parseInputToDeadline(String userInput) {
        String[] deadlineData = splitInput(userInput, Divider.D.getDivisor());
        return new Deadline(deadlineData[0], deadlineData[1]);
    }

    protected static boolean inputIsDeadline(String userInput) {
        return userInput.toLowerCase().contains(Divider.D.getDivisor());
    }

    protected static boolean inputIsEvent(String userInput) {
        return userInput.toLowerCase().contains(Divider.E.getDivisor());
    }


    /**
     * Given user input, checks which command to return
     *  note only the first word is checked for user input,
     *      and the keyword can be found in the CommandPrefix enum.
     * @param fullCommand   full sentence given by user, separated by new line
     * @return  Command command to execute
     */
    public static Command parse(String fullCommand) {
        String stringToRead = fullCommand.trim().toLowerCase();
        if (stringToRead.startsWith(CommandPrefix.ADD.getPrefix())) {
            return new AddCommand();
        } else if (stringToRead.startsWith(CommandPrefix.BYE.getPrefix())) {
            return new ByeCommand();
        } else if (stringToRead.startsWith(CommandPrefix.CLEAR.getPrefix())) {
            return new ClearCommand();
        } else if (stringToRead.startsWith(CommandPrefix.DELETE.getPrefix())) {
            return new DeleteCommand();
        } else if (stringToRead.startsWith(CommandPrefix.DONE.getPrefix())) {
            return new DoneCommand();
        } else if (stringToRead.startsWith(CommandPrefix.ECHO.getPrefix())) {
            return new EchoCommand();
        } else if (stringToRead.startsWith(CommandPrefix.LIST.getPrefix())) {
            return new ListCommand();
        } else if (stringToRead.startsWith(CommandPrefix.MASCOT.getPrefix())) {
            return new MascotCommand();
        } else if (stringToRead.startsWith(CommandPrefix.FIND.getPrefix())) {
            return new FindCommand();
        } else if (stringToRead.startsWith(CommandPrefix.DATE.getPrefix())) {
            return new DateCommand();
        } else {
            return new OopsieCommand();
        }
    }
}
