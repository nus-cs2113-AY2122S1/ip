package duke.task;

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
     * Set the description of the task
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the icon to display 'X' if task is done, and display nothing if not done
     * @return String icon to display
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public int getDoneValue() {
        return (isDone ? 1 : 0);
    }

    public String getCode() {
        return " ";
    }

    public String getBy() {
        return " ";
    }

    public String getAt() {
        return " ";
    }

    /**
     * Mark the boolean variable isDone as true
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
