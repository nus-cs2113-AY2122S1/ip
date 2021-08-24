/**
 * Represents a task.
 *
 * @author Leow Yuan Yang
 * @version 1.0
 * @since 2021-08-25
 */
public class Task {
    protected static int numOfTasks = 0;
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified task description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        numOfTasks++;
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

    public void setDone() {
        isDone = true;
    }
}