package duke.task;

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
        return (isDone ? "[X] " : "[ ] ");
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    public String formatSaveToFile() {
        int isDone = this.isDone ? 1 : 0;
        return " | " + isDone + " | " + description;
    }
}
