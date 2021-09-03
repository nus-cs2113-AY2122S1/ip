public abstract class Task {
    protected String description;
    protected char type;
    protected boolean isDone = false;
    protected static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        totalTasks++;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + type + "][X] " + description;
        } else {
            return "[" + type + "][ ] " + description;
        }
    }

    public void markComplete() {
        isDone = true;
    }

    public int getTotalTasks() {
        return totalTasks;
    }
}
