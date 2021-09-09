package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void undo() {
        this.isDone = false;
    }

    public String getTaskType() {
        return getTaskType();
    }

    public String getTaskID() {
        return getTaskID();
    }

    public String getDate() {
        return getDate();
    }
}
