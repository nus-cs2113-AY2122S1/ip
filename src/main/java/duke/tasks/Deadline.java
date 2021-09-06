package duke.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getStatusIcon() {
        return "[D]" + (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description + " (by: " + by + ")";
    }

    public void setDone() {
        this.isDone = true;
    }
}
