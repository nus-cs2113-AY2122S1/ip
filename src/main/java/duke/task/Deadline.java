package duke.task;

public class Deadline extends Task {
    protected String timing;

    /**
     * Constructs Deadline class given description and timing.
     *
     * @param description Description of deadline.
     * @param by Due date of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.timing = by;
    }

    /**
     * Constructs Deadline class given description, timing and status.
     *
     * @param description Description of deadline.
     * @param by Due date of deadline.
     * @param isDone Status of deadline.
     */
    public Deadline(String description, String by, boolean isDone) {
        this(description, by);
        super.taskDone(isDone);
    }

    /**
     * Gets icon of deadline.
     *
     * @return Icon of deadline.
     */
    public String getIcon() {
        return "D";
    }

    /**
     * Gets timing of deadline.
     *
     * @return timing of deadline.
     */
    public String getTiming() {
        return " (by: " + timing + ")";
    }

    /**
     * Gets time of deadline.
     *
     * @return time of deadline.
     */
    public String getTime() {
        return timing;
    }

    /**
     * Returns string representing event.
     *
     * @return String representing event.
     */
    @Override
    public String toString() {
        return "[" + getIcon() + "]" + super.toString() + getTiming();
    }
}