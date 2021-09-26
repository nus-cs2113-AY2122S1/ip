package task;

/**
 * The Task class represents the underlying task object
 * It is inherited by ToDo, Deadline and Event classes
 * All tasks contain a description string and isDone boolean
 */
public class Task {
    private String description;
    private Boolean isDone;

    /**
     * Sole constructor for all task objects
     * @param desc Represents the name of the task
     * @param status Represents whether the task is marked as complete
     */
    public Task(String description, Boolean isDone) {
        setDescription(description);
        setStatus(isDone);
    }

    /**
     * Sets the description for the current instance of task
     * @param description Represents the name of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the description for the current instance of task
     * @return String Name of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets whether the task is marked as done
     * @param isDone Status as specified by user
     */
    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the string representation of whether a task is marked as done
     * @return String, X if task is marked as complete, blank if it is not
     */
    public String getStatus() {
        String result;
        if (isDone) {
            result = "X";
        } else {
            result = " ";
        }
        return result;
    }

    /**
     * Gets the complete string representation of an individual task.
     * This method should only be called by child classes
     * @return String Task represented by string including type, status and description
     */
    public String toString() {
        return "";
    }

}
