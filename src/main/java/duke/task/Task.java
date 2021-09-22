package duke.task;

public class Task {

    private String description;
    private boolean isDone;

    protected static int taskCount = 0;

    public Task(String desc) {
        setDescription(desc);
        setDone(false);
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toStorageString() {
        return getStatusIcon() + "| " + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
