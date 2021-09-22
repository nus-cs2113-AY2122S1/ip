package duke.task;

/**
 * An abstract class represents a task
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;

    /**
     * Constructor method for <code>Task</code>
     *
     * @param description description of the task
     * @param isDone true if task is done, false otherwise
     */
    public Task(String description, boolean isDone) {
        this.taskDescription = description;
        this.isDone = isDone;
    }

    /**
     * This is the setter method to mark the task as done
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Getter method for taskDescription
     *
     * @return the description of the task in string format
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Getter method for isDone
     *
     * @return true if the task is marked done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Formats the content of the task as String
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.taskDescription);
    }
}
