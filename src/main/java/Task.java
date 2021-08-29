public class Task {

    /** The actual description or name of the task */
    private String description;
    /** Shows if task is completed */
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks if the task is completed and returns corresponding icons
     *
     * @return X if task is already completed else just blank
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
