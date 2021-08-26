public class Task {

    private String description;
    private boolean isDone;

    private static int taskCount = 0;

    public Task(String desc) {
        setDescription(desc);
        setDone(false);
        taskCount++;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String getDescWithStatus() {
        return (this.getStatusIcon() + this.description);
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
