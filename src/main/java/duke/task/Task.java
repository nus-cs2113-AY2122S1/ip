package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of the task.
     *
     * @return "[X]" if task is marked as complete, else returns "[ ]"
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns task type of the task.
     *
     * @return Task type "D", "T", or "E"
     */
    public String getTaskType(){
        return taskType;
    }

    /**
     * Returns description of the task.
     *
     * @return String description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns status icon and description of the task.
     *
     * @return Status icon "[X]" or "[ ]" and description of task.
     */
    public String toString() {
        return getStatusIcon() + this.description;
    }
}


