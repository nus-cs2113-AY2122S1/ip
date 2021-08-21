public class Task {
    protected String description;
    protected boolean isDone;
    protected int taskID;

    public Task(String description, int taskID) {
        this.description = description;
        this.isDone = false;
        this.taskID = taskID;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getTaskID() {
        return taskID;
    }

    public void markAsDone() {
        this.isDone = true;
    }

}

