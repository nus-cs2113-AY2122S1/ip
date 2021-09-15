package duke.task;

/**
 * Represents a task.
 *
 * @author Leow Yuan Yang
 * @version 1.0
 * @since 2021-08-25
 */

public abstract class Task {
    protected static int numOfTasks = 0;
    protected String description;
    protected boolean isDone;
    protected int itemIndex;

    /**
     * Creates a task with the specified task description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        numOfTasks++;
        itemIndex = numOfTasks;
    }

    public static void decrementNumOfTasks() {
        numOfTasks--;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
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