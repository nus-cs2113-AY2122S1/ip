public class Task {
    /** Description of the task */
    protected String description;

    /** Indicates whether the task is done */
    protected boolean isDone;

    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Checks whether the task is marked and returns an icon if it is marked.
     * @return Status icon.
     */
    public String getStatus() {
        //returns the icon to indicate whether the task is completed
        if (isDone) {
            return "X";
        }
        return " ";
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getDescription();
    }
}