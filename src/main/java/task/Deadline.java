package task;

import java.time.LocalDateTime;

/**
 * Represents a deadline. Includes whether it is completed, description, date and time.
 */
public class Deadline extends TaskWithDate {

    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone, dateTime);
    }

    /**
     * Converts deadline into a string for display.
     * @return display string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    /**
     * Converts deadline into a string for saving in a file.
     * @return file string
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString();
    }

}