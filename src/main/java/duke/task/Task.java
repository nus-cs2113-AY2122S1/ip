package duke.task;

/**
 * Represent a generic task.
 * Task contains description of tasks and Boolean isDone to show whether the task is completed
 */
public class Task {
    private static final String TASK_DONE = " 1";
    private static final String TASK_NOT_DONE = " 0";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + getDescription();
    }

    public String toSave() {
        return (isDone ? TASK_DONE : TASK_NOT_DONE) + " | " + description;
    }
}