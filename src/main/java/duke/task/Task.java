package duke.task;

public class Task {

    protected String taskName;
    protected boolean isDone;
    protected static final String DELIMITER = " * ";

    /**
     * Constructor for Task, generates name and status of task.
     *
     * @param description Name of task
     */
    public Task(String description) {
        this.taskName = description;
        this.isDone = false;
    }

    /**
     * Checks if task has been completed and returns the corresponding symbol.
     *
     * @return Whitespace if task is not completed, tick if task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setAsDone() {
        isDone = true;
    }

    public String toSaveFile(String DELIMITER) {
        return DELIMITER + (isDone? 1 : 0) + DELIMITER + taskName;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }

}
