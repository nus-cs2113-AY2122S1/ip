public class Task {
    /** name of task input by user. */
    protected String description;
    /** True means task complete, otherwise if false. */
    protected boolean isDone;

    /**
     * Constructor for new object of Task class.
     *
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Setter to change status of task to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Gets status of task to return with
     * X if complete or blank if incomplete.
     *
     * @return Task status represented by a String.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }
}
