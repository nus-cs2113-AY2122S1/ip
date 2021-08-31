/**
 * Deadline class to represent a task which has a deadline.
 * Parent class is Task class.
 *
 * @param "description" the name of the task.
 * @param "by" deadline of task
 * @return modified message when the toString() method is called.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString() method.
     *
     * @return returns a modified message
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
