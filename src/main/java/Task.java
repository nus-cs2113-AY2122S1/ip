public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone) ? "X" : " "; //mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDataStorageString() {
        char isDoneNumber = (isDone) ? '1' : '0';
        return " | " + isDoneNumber + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +
                this.getDescription();
    }

}
