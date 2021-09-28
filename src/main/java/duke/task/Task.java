package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void taskDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    public String getIcon() {
        return "";
    }

    public String getTiming() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return "";
    }

    public String toString() {
        return getStatusIcon() + " " + this.description ;
    }
}
