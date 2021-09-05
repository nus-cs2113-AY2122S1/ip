public class Task {
    private String description;
    private boolean isDone;

    /**
     * Class Task constructor.
     * Task is initially declared as not completed.
     *
     * @param description Short description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for task description.
     *
     * @return Short description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task as completed.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Checks if a task is completed and returns a corresponding symbol.
     *
     * @return 'X' for completed, and ' ' for not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }

}
