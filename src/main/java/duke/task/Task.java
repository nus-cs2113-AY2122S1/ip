package duke.task;

/**
 * Tasks class is the parent class that stores the description and status of task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the description of the task
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the icon to display '[X]' if task is done, and display '[ ]' if not done
     * @return String icon to display
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public int getDoneValue() {
        return (isDone ? 1 : 0);
    }

    /**
     * Mark the boolean variable isDone as true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toSave() {
        return " ";
    }
}
