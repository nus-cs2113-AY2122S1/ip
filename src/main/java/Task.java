public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Constructor for Task, generates name and status of task.
     *
     * @param description Name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if task has been completed and returns the corresponding symbol.
     *
     * @return Whitespace if task is not completed, tick if task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "=" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }
}
