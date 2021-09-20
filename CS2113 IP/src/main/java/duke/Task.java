package duke;

public class Task {
    protected String deadline;
    protected String date;
    protected String description;
    protected String specificDescription;
    protected String taskType = "";
    protected int index;
    protected boolean isDone;

    public Task(String description, int index) {
        this.description = description;
        this.index = index;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
