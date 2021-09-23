package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        description = "";
        isDone = false;
    }

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Get the task description
     *
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the task description
     *
     * @param description The description to change to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the task status (done or not done)
     *
     * @return The String represent the task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Set the current task 'isDone' to true
     */
    public void setDone() {
        isDone = true;
    }

    public abstract String getTaskType();

    /**
     * Parse the task data into a string to store in a text file
     *
     * @return The string to store in the text file
     */
    public String toStorageString() {
        return String.format("%s//%s//    //%s", getTaskType(),getDescription(),getStatusIcon());
    }

    /**
     * Parse the task data into a string to print out
     *
     * @return The String to represent the task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTaskType(), getStatusIcon(), getDescription());
    }

    ;
}
