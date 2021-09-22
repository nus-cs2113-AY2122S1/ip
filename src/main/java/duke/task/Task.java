package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        description = "";
        isDone = false;
    }

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        isDone = true;
    }

    public abstract String getTaskType();

    public String toStorageString() {
        return String.format("%s//%s//    //%s", getTaskType(),getDescription(),getStatusIcon());
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTaskType(), getStatusIcon(), getDescription());
    }

    ;
}
