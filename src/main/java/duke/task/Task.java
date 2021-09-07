package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    public String getType() {
        return null;
    }

    public String getByDateTime() {
        return null;
    }

    public String getStartAndEndTime() {
        return null;
    }
}
