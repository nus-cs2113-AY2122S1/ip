package tan;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public Task() {
        this("Unnamed");
    }

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
    public void setTaskName(String x) {
        this.name = x;
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        isDone = true;
        System.out.println("Sweet! You've just completed this task: ");
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
}
