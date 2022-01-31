package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

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
     * Sets the task as done
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Gets the task description
     *
     * @return the description of the task in string format
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Gets the isDone value
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
