/**
 * The Task object handles the storage of Task description and its task status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task status of tasks based on the truth of the isDone boolean.
     * @return task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the isDone boolean to true when called upon.
     */
    public void setDone() {
        this.isDone = true;
    }


}
