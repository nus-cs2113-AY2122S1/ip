package main.java.duke.command;

import main.java.duke.task.Deadline;
import main.java.duke.exceptions.DateTimeFormatException;
import main.java.duke.exceptions.DeadlineTimingException;

import java.time.LocalDateTime;

/** Includes the operations needed to execute {@code deadline} user command while managing any input errors. */
public class DeadLineCommand extends TaskList {

    /**
     * Executes the command {@code deadline} on the task list and adds deadline tasks to task list.
     *
     * @param userInput The input given by the user to carry out on tasks list.
     * @throws DeadlineTimingException If the parameter of timing for deadline is missing.
     * @throws DateTimeFormatException If the deadline timing does not meet the format: "dd-MM-yyyy HH:mm".
     */
    public static void executeUserCommand(String userInput)
            throws DeadlineTimingException, DateTimeFormatException {
        int by = userInput.indexOf("/");
        parameter = userInput.split("/by");
        description = parameter[0].trim().split("deadline ");
        if ((by == -1 && (description[1] != null))
                || (parameter.length == 1)
                || (parameter[1].trim().isEmpty())) {
            throw new DeadlineTimingException();
        } else if (!isValidDateTime(parameter[1].trim())) {
            throw new DateTimeFormatException();
        } else {
            LocalDateTime dueDate = LocalDateTime.parse(parameter[1].trim(), formatter);
            addTask(new Deadline(description[1], dueDate));
        }
    }

}
