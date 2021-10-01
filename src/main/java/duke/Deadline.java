package duke;

public class Deadline extends Task {

    private final String taskType = "D";
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
    public String taskString() {
        return this.taskType + " | " + super.getStatusIcon() + " | " + super.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
