public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public void setDone() {
        isDone = true;
    }
}