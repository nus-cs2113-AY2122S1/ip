public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void markTaskAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

}
