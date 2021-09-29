package task;

import java.time.LocalDateTime;

/**
 * Represents an event. Includes whether it is completed, description, date and time.
 */
public class Event extends TaskWithDate {

    public Event(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone, dateTime);
    }

    /**
     * Converts an event into a string for displaying to the user.
     * @return display string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    /**
     * Converts an event into a string for saving into a file.
     * @return file string
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString();
    }

}