package duke.task;

/**
 * Todo is a type under task
 */
public class Todo extends Task {
    protected boolean isDone;

    /**
     * todo initiation
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
        isDone = false;
    }

    /**
     * change status
     * @param done the status
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * get status
     * @return status
     */
    public boolean isDone() {
        return isDone;
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
     * @return null
     */
    @Override
    public String getBy() {
        return null;
    }

    /**
     * get type
     * @return TODO
     */
    @Override
    public Tasktype getType(){
        return Tasktype.TODO;
    }

    /**
     * output string
     * @return string
     */
    @Override
    public String toString() {
        String status = null;
        if (isDone){
            status = "X";
        } else {
            status = " ";
        }
        return  "[" + status + "]" + super.toString();
    }
}