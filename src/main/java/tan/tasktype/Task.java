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
     * Returns the type of task in String.
     *
     * @return Returns the type of task in String format.
     */
    public abstract String getTaskType();

    /**
     * Returns the date of task in String.
     *
     * @return Returns the date of task in String format.
     */
    public abstract String getDateTime();

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
    public String getTaskName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param x The name of the task in String.
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
