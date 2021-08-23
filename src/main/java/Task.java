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
        return (isDone ? "V" : " ");  // mark done task with V
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
