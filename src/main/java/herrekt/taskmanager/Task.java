package herrekt.taskmanager;

public abstract class Task {
    protected final static String SAVE_FILE_SPACER = " / ";
    protected final String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String toSave();

    public abstract String getDescription();

    public boolean isDone() {
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
