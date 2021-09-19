package duke.task;

/**
 * Inherits the <code>Task</code> class, adding in a deadline time to represent a deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs an unfinished deadline task with its deadline time.
     *
     * @param description Description of the task.
     * @param by Deadline time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline task with a description, deadline time and status of the task.
     *
     * @param description Description of the task.
     * @param by Deadline time of the task.
     * @param isDone Indicates whether the task is done or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return Deadline of the task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Overrides the default toString method to display the task in a more suitable format.
     *
     * @return A string showing status of the task, its description and its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
