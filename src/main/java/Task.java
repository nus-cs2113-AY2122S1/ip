/**
 * The Task class manages the description and status of a task.
 */
public class Task {

    /* Contents of the task */
    private String description;
    /* State of the task. True if task is completed. */
    private boolean isCompleted;

    /**
     * Initialise a new incomplete task.
     *
     * @param description Description of task to be done.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Return the icon representing whether the task is completed.
     *
     * @return Status task icon.
     */
    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Return the icon representing the type of the task.
     *
     * @return Task icon.
     */
    public String getTaskIcon() {
        return "?";
    }

    /**
     * Set the task to the completed state.
     */
    public void markAsDone() {
        isCompleted = true;
    }
}
