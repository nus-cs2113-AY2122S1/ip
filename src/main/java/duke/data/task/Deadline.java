package duke.data.task;

/**
 * A deadline task in the task list.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor that sets the description and by.
     *
     * @param description a string that contains the task description
     * @param by a string that contains the deadline for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String of value by, representing the task deadline
     *
     * @return the deadline of the task
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the String representation of the Deadline task
     *
     * @return a String message that contains the status icon, task description and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
