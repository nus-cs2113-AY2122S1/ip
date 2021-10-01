package duke.task;


/**
 * Superclass of the three types of tasks creatable by the user.
 * Contains functions common to Todo, Deadline and Event.
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Creates a task.
     *
     * @param description The Description of the task specified by the user.
     */
    public Task(String description) {
        isDone = false;
        setDescription(description);
    }

    /**
     * Returns a status icon string depending on whether the task is done or not.
     *
     * @return The status icon corresponding to whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Overrides the initial method in the Object class that prints class name @ hash code
     * to print another string when system print is called.
     *
     * @return The default string when system print is called.
     */
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    /**
     * Sets the private member description of the task
     *
     * @param description Description field of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the private member isDone of the task.
     *
     * @return The value of the private member isDone.
     */
    public boolean getisDone() {
        return isDone;
    }

    /**
     * Sets the private member isDone of the task.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Gets the private member description of the task.
     * @return Description field of the task.
     */
    public String getDescription() {
        return description;
    }
}
