package duke.task;

/**
 * Represents a task with task name and status (done or not).
 */
public class Task {
    private final String taskName; //need to change?
    private Boolean isDone;

    /**
     * Task constructor.
     * @param taskName the name/description of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    /**
     * Get the task status (done or not done) as String.
     * @return "X" if done, " " if not done
     */
    public String getTaskStatusInString() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the name/description of the task.
     * @return the name of the task in String
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Check if the task name/description contains a given keywords string.
     * @param keywords keywords to be searched for
     * @return true if the task contains the given keywords, else return no
     */
    public boolean containsKeywords(String keywords) {
        return this.toString().contains(keywords);
    }

    /**
     * Get the isDone attribute.
     * @return the isDone (either true or false)
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Show the full information of the task.
     * @return the full information of the task as String
     */
    @Override
    public String toString() {
        return "[" + this.getTaskStatusInString() + "] " + this.getTaskName();
    }
}

