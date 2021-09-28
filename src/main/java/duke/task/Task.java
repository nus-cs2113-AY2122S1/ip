package duke.task;

public class Task {
    protected String task;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor to create Task object
     *
     * @param task Description of the task
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Retrieves "[X]" for tasks marked done or "[ ]" for tasks not marked done.
     *
     * @return Either "[X]" or "[ ]".
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Records if task is marked done.
     *
     * @return Status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Retrieve more details, usually timings (if any) of deadlines or events.
     *
     * @return timings (if any) of the task.
     */
    public String getMoreDetails() {
        return "";
    }

    /**
     * Retrieve description of the task.
     *
     * @return description of the task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Retrieve the task type.
     *
     * @return Task type.
     */
    public String getType() {
        return type;
    }

    /**
     * set isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overrides string representation to display.
     *
     * @return string representation to display.
     */
    public String toString() {
        return this.getStatusIcon() + this.task;
    }

}