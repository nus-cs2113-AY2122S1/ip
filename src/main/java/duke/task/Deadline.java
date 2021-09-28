package duke.task;

/**
 * Deadline object contains description of the Deadline and date of the Deadline
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    @Override
    public String toSave() {
        return "D |" + super.toSave() + "|" + by;
    }
}
