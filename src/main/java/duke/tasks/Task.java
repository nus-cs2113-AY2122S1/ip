package duke.tasks;

/** Represents a task which is an abstract parent class of ToDo, Deadline, and Event. */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected String doneStatus;

    /**
     * Constructs a {@code Task} with basic information such as description and done status.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.doneStatus = " ";
    }

    /** Sets the {@code Task} as done, which will be denoted by an 'X' in its string format. */
    public void setDone() {
        this.isDone = true;
        this.doneStatus = "X";
    }

    public String getDescription() {
        return description;
    }

    /**
     * Converts the {@code Task} to its string format for display on the user interface.
     *
     * @return {@code Task} in string format
     */
    public String toString() {
        return "[" + doneStatus + "] " + description;
    }

    /**
     * Gets the string format of the date attached to the task.
     *
     * @return Date attached to the {@code Deadline} or {@code Event}, empty string if task is
     * {@code ToDo}
     */
    public abstract String getDate();

    /**
     * <p>Converts {@code Task} to the data string format that is used to store in the storage.</p>
     * <p>Format: {@code TASK_TYPE | DONE_STATUS | TASK_DESCRIPTION | TASK_DATE}</p>
     *
     * @return {@code Task} in data string format.
     */
    public abstract String toDataStringFormat();
}
