public class Task {

    /** Description of task */
    private final String description;
    /** Completion status of task */
    private boolean isDone;

    /**
     * Create a new Task object.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns "X" if the task is done and a whitespace otherwise.
     *
     * @return Completion status icon.
     */
    public String getStatusIcon() {
        // Mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
