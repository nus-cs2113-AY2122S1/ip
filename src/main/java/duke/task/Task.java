package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs task class given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets task as done.
     *
     * @param isDone Status of task.
     */
    public void taskDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets status icon of task.
     *
     * @return Status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets status of task.
     *
     * @return Status of task.
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Gets icon of task.
     *
     * @return Icon of task.
     */
    public String getIcon() {
        return "";
    }

    /**
     * Gets Timing of task.
     *
     * @return timing of task.
     */
    public String getTiming() {
        return "";
    }

    /**
     * Gets description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets time of task
     *
     * @return Time of task.
     */
    public String getTime() {
        return "";
    }

    /**
     * Returns a string representing the task.
     *
     * @return String representing task.
     */
    public String toString() {
        return getStatusIcon() + " " + this.description ;
    }

}
