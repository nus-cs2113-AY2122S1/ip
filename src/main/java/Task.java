public class Task {
    private final String description;
    private boolean isDone;

    //constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //getter
    public String getDescription() {
        return description;
    }

    public String getStatus() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public void markDone() {
        isDone = true;
    }
}
