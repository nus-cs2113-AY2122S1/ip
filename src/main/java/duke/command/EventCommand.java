package main.java.duke.command;

import main.java.duke.exceptions.DateTimeFormatException;
import main.java.duke.exceptions.EventTimingException;
import main.java.duke.task.Event;

import java.time.LocalDateTime;

/** Includes the operations needed to execute {@code event} user command while managing any input errors. */
public class EventCommand extends TaskList {

    /**
     * Executes the command {@code event} on the task list and adds event tasks to task list.
     *
     * @param userInput The input given by the user to carry out on tasks list.
     * @throws EventTimingException If the parameter of timing for event is missing.
     * @throws DateTimeFormatException If the event timing does not meet the format: "dd-MM-yyyy HH:mm".
     */
    public static void executeUserCommand(String userInput)
            throws DateTimeFormatException, EventTimingException {
        int at = userInput.indexOf("/");
        parameter = userInput.split("/at");
        description = parameter[0].trim().split("event ");
        if ((at == -1 && (description[1] != null))
                || (parameter.length == 1)
                || (parameter[1].trim().isEmpty())) {
            throw new EventTimingException();
        } else if (!isValidDateTime(parameter[1].trim())) {
            throw new DateTimeFormatException();
        } else {
            LocalDateTime eventTiming = LocalDateTime.parse(parameter[1].trim(), formatter);
            addTask(new Event(description[1], eventTiming));
        }
    }

}
