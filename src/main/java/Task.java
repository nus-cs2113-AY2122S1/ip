public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) { //for todo
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X, if not its blank
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }

    public String getStoredDataString() {
       return "";
    }
}