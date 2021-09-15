package duke.task;

public abstract class Task {
    public static final char TYPE_TODO = 'T';
    public static final char TYPE_DEADLINE = 'D';
    public static final char TYPE_EVENT = 'E';

    protected String description;
    protected boolean isDone;
    protected char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public abstract String toFileString();

    public boolean isDone() {
        return isDone;
    }

    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns string in list entry format.
     * eg. [T][ ] task1
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return String.format("[%c][%c] %s", taskType, (isDone() ? 'x' : ' '), description);
    }
}
