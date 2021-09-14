public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
