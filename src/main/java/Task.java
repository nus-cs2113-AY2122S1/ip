public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Getters
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    // Setters
    public void markAsDone() {
        isDone = true;
    }
}
