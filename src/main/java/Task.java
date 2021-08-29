public class Task {
    private boolean isDone;
    private String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private char getIcon() {
        return isDone ? 'X' : ' ';
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", getIcon(), description);
    }
}
