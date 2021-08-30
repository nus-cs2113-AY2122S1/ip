public class Task {
    protected String description;
    protected boolean isDone;
    protected int taskId;

    private static int numberOfTasks = 1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskId = numberOfTasks;
        numberOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    //...
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }
}
