package Duke.Tasks;

public class Task {
    protected String description;
    protected String isDone;

    /**
     * Create a Task class with given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = " ";
    }

    /**
     * Get the description of the task.
     *
     * @return description Description of the task
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * Mark the task as done.
     *
     */
    public final void markedAsDone() {
        this.isDone = "X";
    }

    /**
     * Present event in string of prescribed format.
     *
     * @return String presented event description format.
     */
    @Override
    public String toString() {
        String status;
        return "[" + isDone + "] " + this.description;
    }
}
