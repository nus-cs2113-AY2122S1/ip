package duke.task;

public class Task {

    protected String taskName;
    protected boolean isDone;

    /**
     * Constructor for Task, generates name and status of task.
     *
     * @param description Name of task.
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

    /**
     * Gets the boolean value of isDone.
     *
     * @return True or false depending on isDone.
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Gets name of task.
     *
     * @return Name of task as a String.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Changes the name of the tasks.
     *
     * @param newTaskName String containing the new name to replace the old name.
     */
    public void setTaskName(String newTaskName) {
            taskName = newTaskName;
    }

    /**
     * Sets isDone value to true.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns information about task in a sensible form.
     *
     * @return Information about task in a string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }

    /**
     * Returns a String of information about task in format readable by load() method.
     *
     * @param DELIMITER Delimiter separating information in return String.
     * @return String of task information.
     */
    public String toSaveFile(String DELIMITER) {
        return DELIMITER + (isDone? 1 : 0) + DELIMITER + taskName;
    }
}
