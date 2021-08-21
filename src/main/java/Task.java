public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default Constructor
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskDescription() {
        return this.description;
    }

}
