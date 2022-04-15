package shima.task;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    //Constructor
    public Task() {
        this.task = "";
        this.isDone = false;
    }

    /**
     * Abstract method that will return period/endTime for its subclasses event/deadline
     *
     * @return Returns nothing for this abstract method
     */
    public abstract String getTime();

    /**
     * Abstract method that will return the class type for its subclasses
     *
     * @return Returns nothing for this abstract method
     */
    public abstract String getClassType();

    /**
     * Getter for task
     *
     * @return Returns the attribute value task in string
     */
    public String getTask() {
        return task;
    }

    /**
     * Getter for isDone
     *
     * @return Returns the boolean attribute isDone
     */
    public Boolean getDone() {
        return isDone;
    }

    /**
     * Setter for isDone, used to mark task as done
     */
    public void setDone() {
        isDone = true;
    }
}
