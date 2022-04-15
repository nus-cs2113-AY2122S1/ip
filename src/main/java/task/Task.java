package task;

/**
 * Represents a user's task. Contains a description of the task.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String category;

    /**
     * Constructs an instance of task.Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return Status of the task in String format.
     */
    public String getDescription() {
        return description;
    }

    public void setDone() {
        isDone = true;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return Status icon of the task in String format.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Gets the category icon of the task.
     *
     * @return Category icon of the task in String format.
     */
    public String getCategoryIcon() {
        return "[" + category + "]";
    }

}
