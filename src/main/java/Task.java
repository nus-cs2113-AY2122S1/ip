public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[x] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public void markComplete() {
        isDone = true;
    }
}
