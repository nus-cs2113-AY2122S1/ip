public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        isDone = false;
    }

    public String toString() {
        // mark done task with X
        return (isDone ? "[X] " : "[ ] ") + taskDescription;
    }

    public void setDone() {
        isDone = true;
    }
}
