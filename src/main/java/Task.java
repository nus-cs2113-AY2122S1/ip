public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     * Sets the description of Task to input string.
     * Sets the boolean isDone to false.
     *
     * @param description the name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String whether a task is done or not.
     * If the task is done, X is returned.
     * If the task is not done, a whitespace is returned.
     *
     * @return status icon for task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns task' description.
     *
     * @return task' description.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets a task as done by setting isDone as true.
     */
    public void markDone(){
        this.isDone = true;
    }
}
