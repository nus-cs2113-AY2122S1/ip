public class Task {
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

    public char getTaskType() {
        return taskType;
    }

    /**
     * Returns string in list entry format.
     * eg. [T][ ] task1
     *     [D][x] task2
     *
     * @return The formatted string.
     */
    public String getListEntryString() {
        return String.format("[%c][%c] %s", taskType,(isDone() ? 'x' : ' '), description);
    }
}
