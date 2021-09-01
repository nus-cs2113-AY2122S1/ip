public class Task {

    private String description;
    private final char type;
    private boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description Description of the task to add.
     * @param type        type of task to add.
     */
    public Task(String description, char type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the 'Done' status of the task.
     *
     * @return 'Done' status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /* Sets the 'Done' status of the task to TRUE. */
    public void setDone() {
        isDone = true;
    }

    /**
     * Gets the type of task.
     *
     * @return Type of the task.
     */
    public char getType() {
        return type;
    }

    /**
     * Formats details of the Task to a printable string.
     *
     * @return Formatted string of a task.
     */
    public String toFormattedString() {
        return String.format("[%c][%c] %s", this.getType(), isDone ? 'X' : ' ', this.getDescription());
    }
}
