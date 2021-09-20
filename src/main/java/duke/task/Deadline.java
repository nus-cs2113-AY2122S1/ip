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
     * Provides a String representation of the Deadline task.
     *
     * @return The string representation of the Deadline task, including
     * its timing information.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
