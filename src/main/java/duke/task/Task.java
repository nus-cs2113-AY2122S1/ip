package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String parseToStore() {
        int doneStatus = this.isDone ? 1 : 0;
        return String.format(" %d | %s", doneStatus, this.description);
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}