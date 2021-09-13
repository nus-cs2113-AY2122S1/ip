package tan.tasktype;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public Task() {
        this("Unnamed");
    }

    /**
     * Gets the type of task in String.
     *
     * @return Returns the type of task in String format.
     */
    public abstract String getTaskType();

    /**
     * Gets the date of task in String.
     *
     * @return Returns the date of task in String format.
     */
    public abstract String getDateTime();

    /**
     * This is the abstract function to
     * get each task's respective icon.
     *
     * @return The icon in String.
     */
    public abstract String getIcon();

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param x The name of the task.
     */
    protected void setTaskName(String x) {
        this.name = x;
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        isDone = true;
        System.out.println("Sweet! You've just completed this task:");
        System.out.println("[" + this.getStatusIcon() + "] " + name);
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
