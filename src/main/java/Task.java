public class Task {
    protected String description;
    protected boolean isDone;

    // Variable used to identify a particular instance
    protected int taskId;

    // Class variable representing the total number of tasks
    protected static int noOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        noOfTasks++;
        taskId = noOfTasks;
    }

    // Setter methods
    public void markAsDone() {
        this.isDone = true;
    }

    // Getter methods
    public String getDescription() {
        return description;
    }

    public int getTaskId() {
        return taskId;
    }

    public static int getNoOfTasks() {
        return noOfTasks;
    }

    // Mark done task with X
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
}
