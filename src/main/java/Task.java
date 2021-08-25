/**
 * Represents a Task.
 */
public class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Create Task with specified name.
     * @param name The name of task.
     */
    public Task(String name) {
        setName(name);
        setCompleted(false);
    }

    /**
     * Gets the task's name.
     * @return A string representing the task's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the potential task completion status.
     * @return A boolean value representing the task's completion status.
     */
    public boolean getCompleted() {
        return isCompleted;
    }

    /**
     * Set the task's last name.
     * @param name A string containing the task's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the task's completion status.
     * @param isCompleted A boolean containing the task's completion status.
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    // @@author unknown
    // Reused from https://nus-cs2113-ay2122s1.github.io/website/schedule/week3/project.html
    // with minor modifications
    /**
     * Get the task's completion status formatted into.
     * If isCompleted is true, return "X" else returns " ".
     * @return A string representing the status.
     */
    public String getStatusIcon() {
        // Mark done task with X
        return (isCompleted ? "X" : " ");
    }

    /**
     * Get the Item type.
     * Returns empty string for normal Task.
     * Meant to be polymorphed.
     * @return An empty string " "
     */
    public String getItemType() {
        return " ";
    }

    /**
     * Changes a Task into a human-readable format.
     * @return A formatted String of the Task, its status, type and description.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getItemType(), getStatusIcon(), name);
    }
}
