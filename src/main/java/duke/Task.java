package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskCount = 0;
    protected int taskNum;

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
