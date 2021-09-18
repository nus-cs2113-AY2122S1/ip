package duke.task;

/**
 * Represents a task item. A Task object corresponds to an item represented by its description and
 * completion status
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Get the completion status of the task, marked by "X" as completed or " " as incomplete
     * 
     * @return String "X" or " " depending on completion status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toSave() {
        return "";
    }

}
