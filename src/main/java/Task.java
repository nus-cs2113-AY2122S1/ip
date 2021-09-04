public abstract class Task {
    protected String description;
    protected char type;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
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
}
