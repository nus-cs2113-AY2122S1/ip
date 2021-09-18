package duke.tasks;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected String doneStatus;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.doneStatus = " ";
    }

    public void setDone() {
        this.isDone = true;
        this.doneStatus = "X";
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[" + doneStatus + "] " + description;
    }

    public abstract String toDataStringFormat();
}
