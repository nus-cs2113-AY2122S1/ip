package duke.task;

public class Task {
    public String deadline;
    public String date;
    public String description;
    public String specificDescription;
    public String taskType = "";
    public int index;
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
