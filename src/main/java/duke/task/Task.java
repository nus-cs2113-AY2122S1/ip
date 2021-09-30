package duke.task;

/**
 * Task is something that the user want to do
 * Task carries the description and whether it is done
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * task initiation
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get status
     * @return status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * get description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * get type
     * @return TODO
     */
    public Tasktype getType(){
        return Tasktype.TODO;
    }

    /**
     * get description length
     * @return description length
     */
    public int getLength(){
        return description.length() + 6;
    }

    /**
     * get time
     * @return null
     */
    public String getBy() {
        return null;
    }

    /**
     * get status
     * @return status
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * change description
     * @param description the description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * change status
     * @param done the status
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    //...
}
