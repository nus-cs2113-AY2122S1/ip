package duke.task;

/**
 * Parent class for all Tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class initialises the current task's description and isDone status.
     * @param description Task Description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the current task object's description
     * @return Returns the task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mark the current task object as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the current task object's Status Icon.
     * @return Returns the task's status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Converts the current task object into a format that can be stored in a CSV file. Method is overridden in
     * children classes
     * @return Current task object in CSV format
     */
    public String convertToCSV() { return ""; }
}
