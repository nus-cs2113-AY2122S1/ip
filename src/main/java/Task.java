public class Task {
    protected String TaskDescription;
    protected boolean isDone = false;

    public Task(String description) {
        this.TaskDescription = description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }
}
