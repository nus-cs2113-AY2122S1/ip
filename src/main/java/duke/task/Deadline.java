package duke.task;

public class Deadline extends Task {
    protected final String TASK_TYPE = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by: " + by + ")";
    }
}
