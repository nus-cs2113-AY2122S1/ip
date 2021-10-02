/**
 * Parent class Task
 */
public class Task {
    protected String done, type, description;

    public Task(String description) {
        this.done = " ";
        this.type = " ";
        this.description = description;
    }

    public void print(){}

    /**
     *
     * @param x status of the done variable
     */

    public void setDone(String x) {
        this.done = x;
    }

    /**
     *
     * @param description argument for description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + this.description;
    }
}
