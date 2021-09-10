package duke.task;

public abstract class Task {
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String SPACE = " ";

    protected String description;
    protected boolean isDone;
    protected String typeIcon;
    protected String date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public String getDate() {
        return date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + getStatusIcon() + CLOSE_BRACKET + SPACE + description;
    }
}
