public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String task) {
        this.description = task;
    }

    /**
     * Gets the description of the task.
     * 
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     * 
     * @return Returns true if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark the task as done or undone.
     * 
     * @param isDone The "done-ness" of the task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Formats a string indicating if the task is done and the description of the task
     *
     * @return A string indicating if the task is done and the description of the task.
     */
    public String toString() {
        return String.format("[%c] %s", isDone() ? 'X' : ' ' ,this.getDescription());
    }
}
