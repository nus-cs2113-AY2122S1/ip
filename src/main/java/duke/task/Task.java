package duke.task;

/**
 * Represents tasks to be done
 */
public class Task {
    
    protected Boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * sets the name of the task
     *
     * @param name the name of the task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * returns the name of the task
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * return the isDone status of the task
     *
     * @return the isDone status of the task
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * returns "X" is a task is done and " " if the task is not done
     *
     * @return the Status Icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Overrides default toString method with the custom print message
     *
     * @return the custom print message
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}
