package duke.task;

/**
 * Represents a task with a description and can be marked as done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs an unfinished task with a description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with a description and an option to choose whether the task is done.
     *
     * @param description Description of the task.
     * @param isDone Indicates whether the task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon of the task, which indicates whether the task is done.
     *
     * @return "X" if the task is done, and " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
     * Marks the task as done.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Overrides the default toString method to display the task in a more suitable format.
     *
     * @return A string showing status of the task and its description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
