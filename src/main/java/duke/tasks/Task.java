package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and information on whether it is completed or not
 */
public abstract class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Constructs a task by setting isDone as false
     *
     * @param task description of the task
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Converts the task into a String fit for being stored into a data file
     *
     * @return String consisting of all the task information to be stored in a data file
     */
    public abstract String toData();

    /**
     * @return description of task
     */
    public String getTask() {
        return task;
    }

    /**
     * @return the LocalDateTime that the task is due/at if it's a deadline or event
     */
    public LocalDateTime getDT() {
        return null;
    }

    /**
     * @return true if the task is done and false if it isn't
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Change the description of the task
     *
     * @param task new description of the task
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Set task as done
     */
    public void setDone() {
        isDone = true;
    }
}
