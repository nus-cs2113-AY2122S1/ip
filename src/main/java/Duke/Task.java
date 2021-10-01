package Duke;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;

    /**
     * Class constructor for Task
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns description of task
     *
     * @return Description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns status icon according to whether the task is done.
     * [X] for done
     * [ ] for not done
     *
     * @return Status icon
     */
    public String getStatusIcon() {
        return (isDone? "[X]" : "[ ]");
    }

    /**
     * Returns task count
     *
     * @return Number of tasks that has been added
     */
    public static int getTaskCount()  {
        return taskCount;
    }

    /**
     * Sets isDone to boolean b given
     *
     * @param b Boolean to set isDone to
     */
    public void setDone(boolean b) {
        isDone = b;
    }

    /**
     * Sets the task count
     *
     * @param amt Amt to add to the current task count
     */
    public static void setTaskCount(int amt) {
        Task.taskCount += amt;
    }

    /**
     * Used to print task
     *
     * @return String to print when trying to print object Task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
