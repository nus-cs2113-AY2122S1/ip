public class Task {

    private String description;
    private boolean isDone;
    private String type = " ";

    /**
     * Create a new Task object.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
