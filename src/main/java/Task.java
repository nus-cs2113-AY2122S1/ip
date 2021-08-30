public class Task {

    protected String description;
    protected String taskType = "[ ]";
    protected boolean isDone;

    /**
     * Creates new task with description as name.
     * Sets task default as not complete with boolean isDone.
     *
     * @param description string name of task inputted.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the name of the selected task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return description;
    }

    public String getTaskType() {
        return taskType;
    }

    /**
     * Checks if task is already marked as done.
     *
     * @return boolean value: True = done and False = not done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Changes task status to "completed".
     *
     * @param done True if not marked and False if already marked as done.
     */
    public void changeStatusDone(boolean done) {
        isDone = done;
    }

    /**
     * Gets "X" icon to mark completed tasks.
     *
     * @return X if done and [ ] if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String printTask() {
        return this.getTaskType() + this.getStatusIcon() + " " + this.getDescription();
    }
}