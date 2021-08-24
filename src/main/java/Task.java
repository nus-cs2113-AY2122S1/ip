public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }
    public String getTaskName() {
        return taskName;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
