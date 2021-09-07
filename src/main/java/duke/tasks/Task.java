package duke.tasks;

/**
 * Task class to represent a task.
 * Is a parent class to Todo, Event and Deadline classes.
 *
 * @param "description" the name of the task.
 * @return modified message when the toString() method is called.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String whether a task is done or not.
     * If the task is done, X is returned.
     * If the task is not done, a whitespace is returned.
     *
     * @return status icon for task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns task' description.
     *
     * @return task' description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a task as done by setting isDone as true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Overrides the toString() method.
     *
     * @return returns a modified message
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
