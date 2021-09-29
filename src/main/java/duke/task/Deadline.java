package duke.task;

/**
 * deadline is something should be done before a specific time
 * deadline is a type under todo, parallel to event
 * a deadline format "deadline xx /by xx"
 */

public class Deadline extends Todo {
    protected String by;

    /**
     * deadline initiation
     * @param description the description of the task
     * @param by the time for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * change time
     * @param by the time for the task
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * get status
     * @return status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * get time
     * @return the time for the task
     */
    @Override
    public String getBy() {
        return by;
    }

    /**
     * get the description and time length
     * @return description and time length
     */
    @Override
    public int getLength(){
        return description.length() + by.length() + 12;
    }

    /**
     * get the type
     * @return DEADLINE
     */
    @Override
    public Tasktype getType(){
        return Tasktype.DEADLINE;
    }

    /**
     * output string
     * @return string
     */
    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "do by: " + by;
    }
}