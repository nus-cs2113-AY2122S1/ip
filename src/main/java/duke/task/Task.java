package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;

    /**
     * Task class constructor.
     *
     * @param description Task description.
     * @param taskType    Task type.
     */
    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Marks completed tasks with X.
     *
     * @return 'X' for completed and ' ' for not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks completed tasks with 1.
     * To be written to external file.
     *
     * @return '1' for completed and '0' for not completed.
     */
    public String getWrittenIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns task type of a task.
     *
     * @return A task type char.
     */
    public char getTaskType() {
        return taskType;
    }

    /**
     * Returns task description.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns task description.
     *
     * @return Description of the task.
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * Returns task description.
     * To override in subclasses to format task description to external file.
     *
     * @return Description of the task.
     */
    public String toFileFormat() {
        return description;
    }
}
