package duke.task;

import duke.exception.DukeDateTimeFormatException;

import java.time.format.DateTimeFormatter;

/**
 * A deadline timed task with a date-time
 */
public class Deadline extends TimedTask {
    /**
     * Initializes an instance of a deadline
     *
     * @param description the general task description
     * @param by the date and time of the task is due
     * @throws DukeDateTimeFormatException when the date and time input is in wrong formats
     */
    public Deadline(String description, String by) throws DukeDateTimeFormatException {
        super(description,by);
    }
    /**
     * The type of the current task: Deadline
     * @return the String represent the type
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Parse the task data into a string to print out
     * with a by: Time Date deadline
     *
     * @return The String to represent the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String deadlineTime = getDateTime().format(formatter);
        return super.toString() + String.format(" (by: %s)", deadlineTime);
    }
}
