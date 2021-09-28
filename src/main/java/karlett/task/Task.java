package karlett.task;

import karlett.tasklist.TaskList;

import java.io.IOException;

public class Task {

    protected String description;
    protected boolean isDone;
    private TaskList tasks;

    /**
     * Return a Task object, setting its task
     * status to false by default. This constructor
     * is used for user input.
     *
     * @param description details of a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /* constructor used for loading file data */

    /**
     * Return a Task object, setting its task status
     * according to the task status given. This constructor
     * is used for loading file data.
     *
     * @param description details of a task
     * @param isDone task status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : " ");  // mark done task with V
    }

    public void markAsDone(int index) throws IOException {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
