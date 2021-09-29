package duke;

public abstract class Task {
    protected String description;

    protected String taskType;

    protected boolean isDone;

    protected String eventDate;

    public Task(String description) {
        this.description = description;

        this.isDone = false;

        this.taskType = "";

        this.eventDate = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public String getType() {
        return taskType;
    }

    public String getWhen() {
        return eventDate;
    }

    @Override
    public String toString() {
        return ("[" + taskType + "]" + "[" + getStatusIcon() + "] " + description);
    }

}
