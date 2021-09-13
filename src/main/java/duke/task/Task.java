package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Setter methods
    public void markAsDone() {
        this.isDone = true;
    }

    // Getter methods
    public String getDescription() {
        return description;
    }

    // Mark done task with X
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
