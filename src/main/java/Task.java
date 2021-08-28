public class Task {
    protected String taskName;
    protected boolean isCompleted;


    /**
     * Constructor for Task, task is marked as not completed.
     *
     * @param taskName the String containing the name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isCompleted = false;
    }

    /**
     * Marks task as completed.
     */
    public void setCompleted() {
        isCompleted = true;
    }

    /**
     * Returns task name as String.
     *
     * @return the String of the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return the Boolean of whether the task is completed.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Creates a description of the task containing whether it has been completed and its name.
     *
     * @return a String describing the task.
     */
    public String toString() {
        String checkbox;
        if (isCompleted) {
            checkbox = "[X]";
        } else {
            checkbox = "[ ]";
        }
        return checkbox + " " + taskName;
    }
}
