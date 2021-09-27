package hal.task;

/**
 * Represents a generic task. A Task object contains a description, a type and a boolean flag,
 * representing whether the task is done.
 * Task is inherited by all other task objects.
 */
public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    /**
     * <h1>Task</h1>
     * Creates a task with properties description, isdone and type
     *
     * @param description Description of the task
     * @param type Type sets the type of the task
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Retrieves the description of the task.
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the type of the task.
     * @return type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the description to the task.
     * @param description Description to be added to the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns true if the task is completed, false if not completed.
     * @return boolean of the task's completion.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the current task to be completed or unfinished.
     * @param done Boolean representing the status of the current task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Retrieves the current status of the task.
     * Prints out a string representing the status; done or not done.
     * @return string representing the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Sets the current task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

}
