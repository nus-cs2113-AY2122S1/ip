package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String icon;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String getStatusIcon() {
        return (getIsDone() ? "X" : " ");
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract String formatForDataStore();
}
