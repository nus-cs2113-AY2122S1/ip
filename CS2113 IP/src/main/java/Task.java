public class Task {
    protected String description;
    protected String taskType = "";
    protected int index;
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task(String description, int index) {
        this.description = description;
        this.index = index;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
