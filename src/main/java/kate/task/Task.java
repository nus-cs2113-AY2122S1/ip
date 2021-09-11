package kate.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String getTaskInfoForFile();

    /**
     * Retrieves status icon for tasks that are either done or not done
     *
     * @return String "X" if done or " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets task to done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the task information with description
     *
     * @return Description of task
     */
    public String getTaskInfo() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
