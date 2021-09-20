package duke.task;

/**
 * Inherits from Task. Represents a Deadline task
 * and tracks timing information.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with a description and
     * timing information.
     *
     * @param description The description of the Deadline task.
     * @param by          The timing information of the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.setDateAndTime(by);
    }

    /**
     * Gets the timing information of the Deadline task.
     *
     * @return The timing information of the Deadline task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the date and time of the Deadline task.
     *
     * @param line String containing timing information.
     */
    @Override
    public void setDateAndTime(String line) {
        super.setDateAndTime(line);
    }

    /**
     * Provides a String representation of the Deadline task. If this Deadline
     * task has a valid date and time, the timing information will be displayed
     * in a different format (MMM dd yyyy HH:mm).
     *
     * @return The string representation of the Deadline task, including
     * its timing information.
     */
    @Override
    public String toString() {
        if (hasDateTime) {
            by = getDateAndTime();
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
