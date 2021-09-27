public class Deadline extends Task{
    protected String by;

    /**
     * Represents a Deadline added by the user.
     *
     * @param description Description of task with a deadline.
     * @param by Description of the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTime() {
        return this.by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getType() {
        return "D";
    }

    public String toString() {
        return "[D]" + super.getStatus() + super.getDescription() + " (by:" + by + ")";
    }
}
