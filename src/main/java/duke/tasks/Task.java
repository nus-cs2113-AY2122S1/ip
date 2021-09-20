package duke.tasks;

import java.time.LocalDate;

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
    public void setDone() {
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

    /**
     * Psuedo abstract method to change task in list format
     * to saved file format
     *
     * @return string of task in saved file format
     */
    public String toStringStore() {
        return "";
    }

    public LocalDate getDate() {
        return null;
    }
}
