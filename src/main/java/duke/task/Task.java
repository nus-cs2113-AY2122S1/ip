package duke.task;

/**
 * Represent a generic task.
 * Task contains description of tasks and Boolean isDone to show whether the task is completed
 */
public class Task {
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
        return isDone ? " 1 " : " 0 " + "| " + description;
    }
}