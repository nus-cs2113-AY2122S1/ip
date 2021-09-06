package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.description;
    }
}
