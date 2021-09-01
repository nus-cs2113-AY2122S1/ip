/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    /** Deadline for the task (date/time) */
    protected String by;

    /**
     * Creates a task with the specified description and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the task (consisting of the symbol for the task type, the deadline, as well as
     * the string representation from the parent class).
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
