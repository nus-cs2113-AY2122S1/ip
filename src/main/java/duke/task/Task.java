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
    public String getDescription() {

        return this.description;
    }
    public String getStatus() {
        return (isDone ? "1" : "0");
    }
    public String getDate() {
        return "empty";
    }
    public void markDone() {

        this.isDone = true;
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
