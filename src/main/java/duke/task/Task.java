package duke.task;

public abstract class Task {
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String SPACE = " ";

    protected String description;
    protected boolean isDone;
    protected String typeIcon;
    protected String date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon for a task.
     * @return "X" of task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    /**
     * Gets the date of a task (deadline or event).
     * @return date of task
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the status of a task.
     * @return true if task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets a task's status to the given status.
     * @param isDone new status of task
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the description of a task
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets letters representing the type of task
     * @return "T" if task type is todo, "D" if task type is deadline, or "E" if task type is event
     */
    public String getTypeIcon() {
        return typeIcon;
    }

    /**
     * Sets a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + getStatusIcon() + CLOSE_BRACKET + SPACE + description;
    }
}
