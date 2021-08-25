public class Task {

    /* Name of task */
    protected String name;
    /* Status of Task. Is completed when is true */
    protected boolean isDone;

    /* Constructer of an incomplete ask */
    public Task(String name) {
        setName(name);
        this.isDone = false;
    }

    /* Getter for task name */
    public String getName() {
        return name;
    }

    /* Setter for task name */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the icon of status to show if task is completed.
     *
     * @return Icon of status
     */
    public String getStatusIcon(){
        return (isDone ? "X": " ");
    }

    /**
     * Set status of task to be completed.
     */
    public void markAsDone(){
        isDone = true;
    }
}
