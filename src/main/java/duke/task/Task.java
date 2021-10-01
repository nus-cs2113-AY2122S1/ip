package duke.task;

public class Task {
    protected String description;
    public boolean isDone;
    public static int taskCount = 0;
    public int taskNum;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
        taskNum = taskCount;
    }

    /**
     * Retrieves the status icon for the task
     * @return X symbol if task is done, or a whitespace if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
