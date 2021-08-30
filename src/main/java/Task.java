public class Task {
    private static final String DONE_ICON = "[X]";
    private static final String NOT_DONE_ICON = "[ ]";

    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? DONE_ICON : NOT_DONE_ICON);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + taskName;
    }
}
