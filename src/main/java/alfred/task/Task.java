package alfred.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * This constructor instantiates a new Task with a description provided, and a
     * default false for its isDone status.
     * @param description The Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getType();

    public abstract String getDate();

    @Override
    public String toString() {
        return "[" + getStatusIcon() +"] " + description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method sets the Task as done/completed.
     */
    public void setTaskDone() {
        this.isDone = true;
    }
}
