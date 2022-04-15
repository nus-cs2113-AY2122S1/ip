package duke.task;

/**
 * The Task class is the parent class to all tasks added
 * to the Duke program. Every task must have a description (<code>description</code>)
 * and a status (<code>isDone</code>) indicating if it has been done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String listTask() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getIcon() {
        return " ";
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return " ";
    }
}
