package task;

/**
 * Represents a general task
 */
public abstract class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Set the task as completed
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Get the description/name of the task
     * @return The name/description of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Get the completion status of the task
     * @return If the task is completed
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Get the status icon of the task. If completed, an X will be marked.
     * @return The status icon
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Prints the TimedTask as a String
     * @return The task in human-friendly format
     */
    public String toString() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), name);
    }

    /**
     * Prints the task in file-saving format
     * @return The raw string of task that can be saved in the task file
     */
    public String toFileString() {
        return getName();
    }

    /**
     * Get the type icon of the task.
     * @return The type icon
     */
    public abstract String getTypeIcon();
}
