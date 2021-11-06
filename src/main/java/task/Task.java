package task;

/**
 * Represents a task.
 */
public abstract class Task {

    protected String description;

    protected boolean isDone;

    public String getDescription() {
        return description;
    }

    public Task(String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return description;
    }

    public String toFileString() {
        int isCompleted = isDone ? 1 : 0;
        return "|" + isCompleted + "|" + description;
    }
}
