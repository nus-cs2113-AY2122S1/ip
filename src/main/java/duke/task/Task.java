package duke.task;

public class Task {
    protected static final String DELIMITER = "@@@";
    protected String taskName;
    protected boolean isDone;

    /**
     * Class Task constructor.
     * Task is initially declared as not completed.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Getter for taskName.
     *
     * @return String name of the task.
     */
    public String getTaskName() {
        return taskName;
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
        return getStatusIcon() + " " + taskName;
    }

    /**
     * Formats task details to write onto file.
     *
     * @return String containing task details for file format
     */
    public String toFileString() {
        return "N" + DELIMITER + taskName;
    }

}
