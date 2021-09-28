package duke.task;

/**
 * Class that encapsulates a Deadline task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a Deadline object
     *
     * @param description The description of the deadline task
     * @param by The deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String parseDataIntoString() {
        return "D" + super.parseDataIntoString() + " | " + this.by;
    }
}
