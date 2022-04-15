package duke.task;

/**
 * Class that encapsulates a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string corresponding to the status of the task.
     *
     * @return an "X" if task is marked as done, else a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task for user output.
     *
     * @return A string representation of the task formatted for user output,
     * consisting of its status and description.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Returns a string representation of the task to write to file.
     *
     * @return A string representation of the task formatted for file writing,
     * consisting of its description and status.
     */
    public String parseDataIntoString() {
        char isDoneChar = this.isDone ? '1': '0';
        return " | " + isDoneChar + " | " + this.description;
    }

    /**
     * Obtains and returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
