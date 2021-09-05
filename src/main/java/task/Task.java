package task;

public class Task {
    protected String taskName;
    protected boolean isDone;

    // Constructor
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    // Getters and Setters
    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    // Function to get StatusIcon based on isDone
    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }
}
