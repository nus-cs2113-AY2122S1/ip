package duke.task;

public class Task {

    private static final String TASK_IS_DONE = "1";
    private static final String TASK_IS_NOT_DONE = "0";
    private static final String TASK_COMPLETED_ICON = "X";
    private static final String TASK_NOT_COMPLETED_ICON = " ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the user input and check if it is empty or null
     *
     * @param validString the user String input
     * @return true if string is empty or null and false otherwise
     */
    public boolean isStringNullOrEmpty(String validString) {
        return validString == null || validString.isEmpty();

    }

    /**
     * Obtains description of task
     *
     * @return get the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is completed and returns corresponding icons
     *
     * @return X if task is already completed else just blank
     */
    private String getStatusIcon() {
        return (isDone ? TASK_COMPLETED_ICON : TASK_NOT_COMPLETED_ICON);
    }

    /**
     * Sets the task as completed
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Check the completion status of a task
     *
     * @return get the isDone value
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Formats task object to a savable string format
     *
     * @return formatted task object as a String to be saved
     */
    public String saveToText() {
        return (isDone ? TASK_IS_DONE : TASK_IS_NOT_DONE) + " | " + description;
    }

    /**
     * Overwrites default toString method with the custom print message
     *
     * @return Customised string message for task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
