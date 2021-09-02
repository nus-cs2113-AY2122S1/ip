public class Task {
    protected String description;
    protected boolean isDone;
    protected String by;

    public Task(String description) { //for todo
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String by) { //for event and deadline
        this.description = description;
        this.by = by;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X, if not its blank
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }
}