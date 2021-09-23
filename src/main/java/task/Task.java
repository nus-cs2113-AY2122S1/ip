package task;

public abstract class Task {
    /** Description of the task */
    protected String description;

    /** Indicates whether the task is done */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Checks whether the task is marked and returns an icon if it is marked.
     * @return Status icon.
     */
    public String getStatus() {
        if (isDone) {
            return "X";
        }
        return " ";
    }


    /**
     * Abstract method used to convert Task type to a string format used to
     * store it in the text file.
     * @return String containing all the details of the task
     */
    public abstract String toFileFormat();

    @Override
    public abstract String toString();
}