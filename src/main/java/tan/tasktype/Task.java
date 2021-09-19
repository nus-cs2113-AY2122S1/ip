package tan.tasktype;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task() {
        this("Unnamed");
    }

    /**
     * Returns the type of task in String.
     *
     * @return Returns the type of task in String format.
     */
    public abstract String getTaskType();

    /**
     * Returns the date of task in String formatted.
     *
     * @return The date of task in String format.
     */
    public abstract String getDateTimeInString();

    /**
     * Returns the current task's Date, unformatted, in String.
     * E.g 1996-10-29
     *
     * @return Date of task in unformatted String.
     */
    public abstract String getDateTimeForStorage();

    /**
     * Returns each respective task's icon.
     *
     * @return The icon in String.
     */
    public abstract String getIcon();

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskDescription() {
        return description;
    }

    /**
     * Sets the name of the task.
     *
     * @param x The name of the task in String.
     */
    protected void setTaskName(String x) {
        this.description = x;
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        isDone = true;
        System.out.println("Sweet! You've just completed this task:");
        System.out.println("[" + this.getStatusIcon() + "] " + description);
    }

    /**
     * Returns the status icon of
     * the current task based on it's status.
     *
     * @return The icon of its current status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the current status in boolean.
     *
     * @return Status of Task in Bool.
     */
    public Boolean getStatus() {
        return isDone;
    }
}
