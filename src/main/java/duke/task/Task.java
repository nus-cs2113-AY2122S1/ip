package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskType getType() {
        return type;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getFileStringFormat() {
        return String.format("%d | %s", isDone ? 1 : 0, this.description.trim());
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type.getTaskLabel(), this.getStatusIcon(), this.description.trim());
    }
}
