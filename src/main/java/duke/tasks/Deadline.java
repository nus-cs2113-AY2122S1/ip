package duke.tasks;

/**
 * Deadline is a Sub-class that inherits from Task Class
 * A Deadline object is represented by a description of the task and when the Deadline is due by.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D|" + super.toFileFormat() + "|" + by;
    }
}
