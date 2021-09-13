package duke;

public abstract class Task {
    private static final String DONE_ICON = "[X]";
    private static final String NOT_DONE_ICON = "[ ]";

    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (getIsDone()? DONE_ICON : NOT_DONE_ICON);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + taskName;
    }
}
