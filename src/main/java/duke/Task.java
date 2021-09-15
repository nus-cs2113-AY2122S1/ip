package duke;

public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String textFormatting() {
        return String.format(this.getClass().getName() + ";" + isDone + ";" + description);
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    public String toString() {
        return getStatusIcon() + "] ";
    }
}

