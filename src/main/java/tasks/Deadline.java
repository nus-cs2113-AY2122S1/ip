package tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Sets the deadline of this deadline task.
     *
     * @param by Deadline to be set.
     */
    public void setDeadline(String by) {
        this.by = by;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return Deadline of the task.
     */
    public String getDeadline() {
        return by;
    }

    /**
     * Returns the task information in String format.
     *
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}