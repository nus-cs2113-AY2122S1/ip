package duke.task;

public class Task {
    protected String task;
    protected boolean isDone;
    protected String type;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public boolean isDone() {
        return isDone;
    }

    public String getMoreDetails() {
        return "";
    }

    public String getTask() {
        return task;
    }

    public String getType() {
        return type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatusIcon() + this.task;
    }

}