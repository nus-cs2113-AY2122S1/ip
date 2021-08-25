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

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    public boolean isDone() {
        return isDone;
    }

    public void changeStatusDone(boolean done) {
        isDone = done;
    }
}