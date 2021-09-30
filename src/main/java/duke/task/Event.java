package duke.task;

import duke.exception.DukeDateTimeFormatException;

import java.time.format.DateTimeFormatter;

/**
 * An event timed task with a date-time
 */
public class Event extends TimedTask {
    /**
     * Initializes an instance of an event
     *
     * @param description the general task description
     * @param at the date and time of the task is at
     * @throws DukeDateTimeFormatException when the date and time input is in wrong formats
     */
    public Event(String description, String at) throws DukeDateTimeFormatException {
        super(description, at);
    }

    /**
     * The type of the current task: Event
     * @return the String represent the type
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Parse the task data into a string to print out
     * with a at: Time Date event time
     *
     * @return The String to represent the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String eventTime = getDateTime().format(formatter);
        return super.toString() + String.format(" (at: %s)", eventTime);
    }
}
