public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the string, by, that determines the deadline of the task
     * @return String by
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Set the string, by
     * @param by the deadline of task
     */
    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription() +
                " (by: " + this.getBy() + ")";
    }
}
