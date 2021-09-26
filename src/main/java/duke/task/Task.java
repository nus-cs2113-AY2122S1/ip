package duke.task;

/**
 * Represents a task.
 *
 * @author Leow Yuan Yang
 * @version 1.0
 * @since 2021-08-25
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified task description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * @return status icon represents whether the task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskIcon() {
        return " ";
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDue() {
        return null;
    }
}