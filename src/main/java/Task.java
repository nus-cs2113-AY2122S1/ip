public class Task {
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String SPACE = " ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + getStatusIcon() + CLOSE_BRACKET + SPACE + description;
    }
}
