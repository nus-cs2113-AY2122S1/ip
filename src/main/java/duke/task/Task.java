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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
