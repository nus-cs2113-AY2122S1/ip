package duke;

/**
 * Represents a <code>Task</code> with a deadline.
 */
public class Deadline extends Task {
    private static final String DEADLINE_ICON = "[D]";

    private String by;

    /**
     * Instantiates a <code>Deadline</code> <code>Task</code>.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return Deadline of the task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Formats the <code>Deadline</code> to <code>String</code>.
     *
     * @return Formatted deadline to user-friendly output.
     */
    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString()
                + " (by: " + by + ")";
    }
}
