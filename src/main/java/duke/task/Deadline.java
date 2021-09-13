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
     * The constructor method. Initialize task description,
     * status to "not done", and deadline.
     *
     * @param description Task description.
     * @param by Task deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * The constructor method. Initialize task description,
     * status according to the given parameter, and deadline.
     *
     * @param description Task description.
     * @param by Task deadline.
     * @param isDone Initial status.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String serialize() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
