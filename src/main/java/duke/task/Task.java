package duke.task;

public class Task {

    private String description;
    private boolean isDone;
    /** Type of task (Todo [T], Deadline [D], Event [E]). */
    private String type = " ";

    /**
     * Create a new Task object.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the type, status and description of the task.
     *
     * @return String of type, status and description of the task.
     */
    @Override
    public String toString() {
        return "[" + getType() + "][" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the description of the task with due date or event date if applicable.
     *
     * @return String description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the description of the task without due date or event date.
     *
     * @return String description of the task.
     */
    public String getRawDescription() {
        return description;
    }

    /**
     * Returns "X" if the task is done and a whitespace otherwise.
     *
     * @return Completion status icon.
     */
    public String getStatusIcon() {
        // Mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Returns task type.
     *
     * @return String of task's type
     */
    public String getType() {
        return type;
    }

    /**
     * Set task's type.
     * @param type String of task type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
