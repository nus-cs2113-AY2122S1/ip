package Duke;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone? "[X]" : "[ ]");
    }

    public static int getTaskCount()  {
        return taskCount;
    }

    public void setDone() {
        isDone = true;
    }

    public static void setTaskCount() {
        Task.taskCount += 1;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

}
