public class Task {
    protected String description;
    protected boolean isDone;
    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

}
