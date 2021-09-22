package duke.task;

public class Deadline extends Task {
    protected String by;
    protected String type;

    public Deadline(boolean isDone, String taskName, String by) {
        super(taskName);
        this.isDone = isDone;
        this.by = by;
        this.type = "D";
    }
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveFile(String DELIMITER) {
        return "D" + super.toSaveFile(DELIMITER) + DELIMITER + by;
    }
}
