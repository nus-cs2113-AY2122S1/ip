package duke.templar;

/**
 * The parent class of task that is extended by todo, event, and deadline
 * that sets and gets the task's completion status
 */
public class Task
{
    protected String description;
    protected boolean isDone;

    public Task(String description)
    {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets a marker to indicate task's completion status
     * @return marker "x" || " "
     */
    public String getDoneStatus()
    {
        if (isDone) {
            return "x";
        }
        return " ";
    }

    /**
     * Sets task as done
     * @param done
     */
    public void setDone(boolean done)
    {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[" + getDoneStatus() + "] " + description;
    }
}

