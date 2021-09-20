package duke.task;

public abstract class Task {
    public static final char TYPE_TODO = 'T';
    public static final char TYPE_DEADLINE = 'D';
    public static final char TYPE_EVENT = 'E';

    public static final String COLUMN_SEPARATOR = " | ";

    protected String description;
    protected boolean isDone;
    protected char taskType;

    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public abstract String toFileString();

    public String getDescription() {
        return description;
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
    @Override
    public String toString() {
        return String.format("[%c][%c] %s", taskType, (isDone() ? 'x' : ' '), description);
    }

    /**
     * Returns string in file line entry format.
     * eg. T | 0 | Task 1
     *
     * @return The formatted string.
     */
    protected String generateFileString(String[] items) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < items.length; i += 1) {
            if (i > 0) {
                stringBuilder.append(COLUMN_SEPARATOR);
            }

            stringBuilder.append(items[i]);
        }

        return stringBuilder.toString();
    }
}
