package duke.task;

/**
 * Represents a task in an ArrayList
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class
     *
     * @param description the content of the task
     */
    public Task(String description) {

        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     *
     * @return String the description of the task
     */
    public String getDescription() {

        return description;
    }

    /**
     * Returns the current status of the task
     *
     * @return String "1" if the task is done, otherwise returns "0"
     */
    public String getStatusIcon() {

        return (isDone ? "1" : "0"); // mark done task with X
    }

    /**
     * Marks the task status as done
     */
    public void markAsDone() {

        this.isDone = true;
    }

    /**
     * Returns a String representation containing status icon and description
     *
     * @return String representation of a task
     */
    @Override
    public String toString() {

        return "[" + getStatusIcon() + "] " + description;
    }

}