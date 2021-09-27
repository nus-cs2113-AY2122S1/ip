package Duke.Task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string of the status icon.
     * "X" if the task is marked as done and " " if it is not.
     *
     * @return Returns the status icon in a string.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Returns the description of the task,
     * including the task icon, status, task name and  date and time of the event or deadline task
     * in a single string for printing purposes.
     *
     * @return Returns the task icon, status and task name of the task in a single string.
     */
    public String toString() {
        return description;
    }

    /**
     * Returns the description which only includes the task name and date and time of the event or deadline task.
     *
     * @return Returns the task name and date and time of the event or deadline task in a string format.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the boolean value of isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
