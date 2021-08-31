public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task object to be stored in Duke's list of Tasks,
     * sets the name of the task as the name passed in by the user and
     * marks the task as incomplete
     *
     * @param description the name of the task to be created
     */
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the icon to be printed while listing out tasks stored by Duke
     * Done tasks are denoted by X
     *
     * @return the icon to be used while listing tasks on Duke to standard output
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
