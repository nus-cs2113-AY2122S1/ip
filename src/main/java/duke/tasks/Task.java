package duke.tasks;

/**
 * Represents a task object which is an abstract parent class of ToDo, Deadline, and Event.
 */

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected String doneStatus;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.doneStatus = " ";
    }

    public void setDone() {
        this.isDone = true;
        this.doneStatus = "X";
    }

    public String toString() {
        return "[" + doneStatus + "] " + description;
    }

    /**
     * Converts <code>Task</code> to the data string format that is used to store in the storage.
     *
     * @return The <code>Task</code> in data string format.
     */

    public abstract String toDataStringFormat();
}
