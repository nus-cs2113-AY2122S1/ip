package duke.data.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected final String doneSign = "X";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }
    public abstract String getType();
    public abstract String store();
    public String getStatusIcon() {
        return (isDone ? doneSign : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean containsKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
