public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //Mark completed tasks with an X
    }

    public void markAsDone() {
        isDone = true;
    }
}
