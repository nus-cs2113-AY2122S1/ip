package duke.tasks;

public class Deadline extends Task {
    private TaskType type = TaskType.DEADLINE;
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String printTask() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by:" + by + ")";
    }

    @Override
    public String saveTask() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }
}
