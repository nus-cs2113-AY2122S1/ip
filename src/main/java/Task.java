public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.taskDescription);
    }
}
