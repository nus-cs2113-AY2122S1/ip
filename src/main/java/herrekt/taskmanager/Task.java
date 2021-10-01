package herrekt.taskmanager;

public abstract class Task {
    protected final static String SAVE_FILE_SPACER = " / ";
    protected final static String SPACER = " ";
    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected abstract String toSave();

    protected abstract String getDescription();

    protected boolean equals(Task otherTask) {
        return this.getDescription().equals(otherTask.getDescription());
    }

    protected boolean isDone() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
