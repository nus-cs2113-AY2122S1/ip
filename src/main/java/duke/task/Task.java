package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String listTask() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getIcon() {
        return " ";
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return " ";
    }
}
