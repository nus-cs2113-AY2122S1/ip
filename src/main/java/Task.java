public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String task) {
        this.description = task;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Formats a string indicating if the task is done and the description of the task
     *
     * @return A string indicating if the task is done and the description of the task.
     */
    public String getStatusString() {
        return String.format("[%c] %s", isDone() ? 'X' : ' ' ,this.getDescription());
    }
}
