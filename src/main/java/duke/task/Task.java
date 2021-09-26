package duke.task;

/**
 * Represents a {@code Task} in the user's tasklist that is managed by the {@code TaskManager}.
 * It is the parent class of the {@code ToDo}, {@code Deadline} and {@code Event} subclasses.
 */
public abstract class Task {

    /**
     * Represents the description of the task.
     */
    protected String description;

    /**
     * Indicates whether the Task has been marked as done.
     */
    protected boolean isDone;

    /**
     * Constructor that takes in {@code description} only.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor that takes in both {@code description} and {@code isDone}.
     *
     * @param description description of the task.
     * @param isDone      whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets task description of task.
     *
     * @return a String containing both task description and isDone status.
     */
    public String getTaskDescription() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Gets status icon of task.
     *
     * @return a String containing a symbol to indicate whether task is done.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Gets task information that matches the format of the tasks stored in duke.txt.
     *
     * @return a String in the format of duke.txt task entry.
     */
    public abstract String getTaskFileFormat();

}
