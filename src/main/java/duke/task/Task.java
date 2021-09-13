package duke.task;

public class Task {
    protected static final String DELIMITER = "@@@";
    protected String description;
    protected boolean isDone;

    /**
     * Class Task constructor.
     * Task is initially declared as not completed.
     *
     * @param description Short description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the task as completed.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Checks if a task is completed and returns a corresponding symbol.
     *
     * @return 'X' for completed, and ' ' for not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Formats task details to write onto file.
     *
     * @return String containing task details for file format
     */
    public String toFileString() {
        return "N" + DELIMITER + description;
    }

}
