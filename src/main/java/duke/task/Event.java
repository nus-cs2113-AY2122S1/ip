package duke.task;

/**
 * event is something that happens at a specific time
 * event is a type under todo, parallel to deadline
 * a event format " event xx /at xx"
 */

public class Event extends Todo {
    protected String by;

    /**
     * event initiation
     * @param description the description of the task
     * @param by the time for the task
     */
    public Event(String description, String by) {
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
     * @return EVENT
     */
    @Override
    public Tasktype getType(){
        return Tasktype.EVENT;
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