package task;

public abstract class Task {
    public abstract String getType();
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return (isDone ? "1" : "0"); //returns 1 if done, 0 if not done
    }
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
