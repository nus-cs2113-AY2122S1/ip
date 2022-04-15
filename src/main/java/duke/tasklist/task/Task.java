package duke.tasklist.task;

import duke.tasklist.TaskManager;

/**
 * Base class for all types of tasks.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType = "";

    /**
     * Creates a new Task object, sets the task name, done status to false, and task type as empty by default.
     * @param description String of task name.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    /**
     * Returns the completion status of the task; X if done, otherwise blank.
     * @return String marker for the completion status of a task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Creates and returns the format used to display the task to the user.
     * @return String data used for displaying to user.
     */
    public String toString() {
        return this.description;
    }

    /**
     * Creates and returns the format used in the storage file.
     * @return String data used in storage file.
     */
    public String toDataFormat() {
        return this.taskType + " | " + (getStatusIcon().equals(TaskManager.STATUS_DONE) ? "1" : "0")
                + " | " + this.description;
    }
}
