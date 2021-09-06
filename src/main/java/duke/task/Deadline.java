package duke.task;

/**
 * The Deadline class is a subclass of Task. It
 * provides an additional parameter, deadline.
 *
 * @author richwill28
 */
public class Deadline extends Task {
    /** Task deadline */
    protected String by;

    /**
     * The constructor method. Initializes the task
     * description, status, and deadline.
     *
     * @param description Task description.
     * @param by Task deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of Deadline.
     *
     * @return The string representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
