package duke;

/**
 * Task class used by bot.
 *
 * @author pragyan01
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor of task
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return status of task as string.
     *
     * @return String status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Setter method of task
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Getter method of task.
     *
     * @return String description of task
     */
    public String toString() {
        return description;
    }

    /**
     * Method to return String that has been stored in the Arraylist.
     *
     * @return String stored in the ArrayList
     */
    public String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "taskType" + description + " | " + done;
    }
}

