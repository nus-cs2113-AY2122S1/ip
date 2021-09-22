package duke.task;

public class Task {
    public String getDescription() {
        return description;
    }

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Modify task string into txt file format
     *
     * @return format of data
     */
    public String getStoreDataString() {
        return "";
    }

    /**
     * Marks done task with X
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }
}

