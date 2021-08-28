public class Task {

    /**
     * The description of the Task
     */
    protected String descr; //descr as an abbreviation for description

    /**
     * The done status of the Task
     */
    protected boolean isDone;

    /**
     * The total number of Tasks added
     */
    private static int totalTasks = 0;

    public Task(String descr) {
        this.descr = descr;
        isDone = false;
        totalTasks++;
    }

    /**
     * Set the Task object isDone value to the given boolean
     *
     * @param doneStatus boolean representing the isDone status to be set to
     */
    public void setDone(boolean doneStatus) {
        isDone = doneStatus;
    }

    /**
     * Returns the total number of Tasks added
     *
     * @return the current value of totalTasks
     */
    public static int getTotalTasks() {
        return totalTasks;
    }

    /**
     * Returns an icon representing the done status of the Task object
     *
     * @return "[X]" if isDone is true, "[ ]" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //icon "[X]" as done and "[ ]" as not done
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + descr;
    }

}
