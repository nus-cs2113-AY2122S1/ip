package duke.task;

public class Deadline extends Task {

    protected String by;
    protected String type = "D";

    /**
     * Constructor to create Deadline object.
     *
     * @param description Deadline description.
     * @param by The time the deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieve MoreDetails (timings) of deadlines.
     *
     * @return timings of the Deadline.
     */
    public String getMoreDetails() {
        return this.by;
    }

    /**
     * Retrieve the task type.
     *
     * @return Task type: "D".
     */
    public String getType() {
        return type;
    }

    /**
     * Overrides string representation to display.
     *
     * @return string representation to display.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}