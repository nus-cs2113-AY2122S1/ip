public class Task {
    public static final char TYPE_TODO = 'T';
    public static final char TYPE_DEADLINE = 'D';
    public static final char TYPE_EVENT = 'E';

    private String description;
    private boolean isDone;
    private char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

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
    public String getListEntryString() {
        return String.format("[%c][%c] %s", taskType,(isDone() ? 'x' : ' '), description);
    }
}
