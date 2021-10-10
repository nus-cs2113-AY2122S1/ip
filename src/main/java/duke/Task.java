package duke;

/**
 * Parent class of all task classes
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor used to create a new Task object.
     *
     * @param description all details of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter used to check if a task has been marked as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * To mark a task as done so that it will be listed as completed to the user.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to return the task from the ArrayList.
     *
     * @return description of the task
     */
    public String toString() {
        return description;
    }
}
