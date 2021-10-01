package duke.task;

/**
 * Class that encapsulates a Deadline task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a Deadline object
     *
     * @param description The name of the deadline task
     * @param by The deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task for user output
     *
     * @return A string representation of the Deadline task formatted for user output,
     * consisting of its description, status and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns a string representation of the Deadline task to write to file
     *
     * @return A string representation of the Deadline task formatted for file writing,
     * consisting of its description, status and deadline
     */
    @Override
    public String parseDataIntoString() {
        return "D" + super.parseDataIntoString() + " | " + this.by;
    }
}
