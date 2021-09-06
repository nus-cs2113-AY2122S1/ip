public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type.getTaskLabel(), this.getStatusIcon(), this.description);
    }
}
