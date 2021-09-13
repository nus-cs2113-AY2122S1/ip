package duke.task;

public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.taskDescription = description;
        this.isDone = isDone;
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

    public boolean isDone() {
        return this.isDone;
    }

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.taskDescription);
    }
}
