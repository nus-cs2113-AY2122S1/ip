package duke.task;
import java.io.Serializable;

public abstract class Task implements Serializable {

    private String title = "";
    private boolean doneStatus = false;

    protected String getType() {
        return "task";
    }

    private Task() {
        // prevent uninitialised task
    }

    public Task(String title) {
        this.doneStatus = false;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDoneStatus(boolean status) {
        this.doneStatus = status;
    }

    public String getStatusIcon() {
        return this.doneStatus ? "x" : " ";
    }

    public String toString() {
        return "[" + Character.toUpperCase(this.getType().charAt(0)) + ']' +
            '[' + this.getStatusIcon() + ']' + ' ' + this.title;
    }
}
