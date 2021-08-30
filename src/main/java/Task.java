import java.util.List;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public Task() {
        this("Unnamed");
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param x The name of the task.
     */
    public void setTaskName(String x) {
        this.name = x;
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        isDone = true;
        System.out.println("Sweet! You've just completed this task: ");
        System.out.println("[" + this.getStatusIcon() + "] " + name);
    }

    /**
     * Returns the status icon of
     * the current task based on it's status.
     *
     * @return The icon of its current status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
