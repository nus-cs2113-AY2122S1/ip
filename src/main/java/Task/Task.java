package Task;

/**
 * superclass Task containing description of tasks and
 * boolean isDone to check if task is done or not
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

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + description;
    }
}
