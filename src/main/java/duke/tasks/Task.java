package duke.tasks;

/**
 * Represents a task object which is an abstract parent class of ToDo, Deadline, and Event.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected String doneStatus;

    /**
     * Constructs a <code>Task</code> with basic information such as description and done status.
     *
     * @param description <code>String</code> description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.doneStatus = " ";
    }

    /** Sets the <code>Task</code> as done, which will be denoted by an 'X' in its <code>String</code>
     * format.
     */
    public void setDone() {
        this.isDone = true;
        this.doneStatus = "X";
    }

    public String getDescription() {
        return description;
    }

    /**
     * Converts the <code>Task</code> object to its string format for display on the user interface.
     *
     * @return <code>Task</code> in string format
     */
    public String toString() {
        return "[" + doneStatus + "] " + description;
    }

    public abstract String getDate();

    /**
     * Converts <code>Task</code> to the data string format that is used to store in the storage.
     *
     * @return The <code>Task</code> in data string format.
     */
    public abstract String toDataStringFormat();
}
