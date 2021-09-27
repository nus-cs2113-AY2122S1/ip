package duke.task;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public abstract class Task implements Serializable {

    private String title = "";
    private boolean doneStatus = false;
    protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /*
     * This is mainly to provide a unified interface to determine the type
     * @return A unique string denoting the type
     */
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

    /*
     * Get the title of a task
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /*
     * Set the title of a task
     * @param title The new title to be set to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDoneStatus(boolean status) {
        this.doneStatus = status;
    }

    /*
     * Get the icon reflecting the status whether the task is done
     * @return the icon
     * @see doneStatus
     */
    public String getStatusIcon() {
        return this.doneStatus ? "x" : " ";
    }

    public String toString() {
        return "[" + Character.toUpperCase(this.getType().charAt(0)) + ']' +
            '[' + this.getStatusIcon() + ']' + ' ' + this.title;
    }
}
