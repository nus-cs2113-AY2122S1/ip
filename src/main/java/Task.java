/**
 * Represents a task that is added to the list by a user. A Task object contains a description
 * represented by a String and the status of the task, represented by boolean.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
//    protected boolean by;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public abstract String saveToFile();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
