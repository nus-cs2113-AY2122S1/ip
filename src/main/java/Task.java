public class Task {
    protected String taskDescription;
    protected boolean isDone = false;

    public Task(String description) {
        this.taskDescription = description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }
}
