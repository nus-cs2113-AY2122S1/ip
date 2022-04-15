package duke.task;

/**
 * General Task class which more specific task classes will inherit.
 * @author Mohamed Irfan
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor method to initiate the task's description and is done status.
     * @param description Description of the task.
     * @param isDone The status of tasks whether it is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is marked as done and returns the appropriate icon.
     * @return icon which can be either [X] or [ ]
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Helper method that converts the task to a format suitable to be saved in local file.
     * @return task in a suitable format to save in local file
     */
    public String formatSaveToFile() {
        int isDone = this.isDone ? 1 : 0;
        return " | " + isDone + " | " + description;
    }
}
