package duke.task;

public class Task {

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

    public String toString() {
        return "[" + doneStatus + "] " + description;
    }
}
