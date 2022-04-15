package duke.task;

/**
 * Implements the class Deadline, which has a description, a done status
 * and a detail stating when it is to be completed by.
 */
public class Deadline extends Task {

    private final String taskType = "D";
    /** The time the Deadline has to be completed by. */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        super.setDone(isDone);
    }

    @Override
    public boolean contains(String search) {
        boolean descriptionContains = super.contains(search);
        boolean byContains = by.toLowerCase().contains(search);
        return descriptionContains || byContains;
    }

    @Override
    public String taskString() {
        return this.taskType + " | " + super.getStatusIcon() + " | " + super.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
