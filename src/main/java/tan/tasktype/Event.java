package tan.tasktype;

public class Event extends Task {

    private String dateTime;
    private final String eventIcon = "E";

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public Event(String description, Boolean isDone, String dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns the Task's type in String.
     *
     * @return Returns the string "Event".
     */
    @Override
    public String getTaskType() {
        return "Event";
    }

    /**
     * Returns the icon for Event tasks.
     *
     * @return Returns the String "E"
     */
    @Override
    public String getIcon() {
        return eventIcon;
    }

    /**
     * Returns the do-by date for the task
     * in String.
     *
     * @return Returns do-by date in String.
     */
    @Override
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Returns the formatted string to print the task nicely.
     * This will return the whole string
     * including its icon, status, task name
     * and do-by date.
     *
     * @return The task icon, status, name and do-by date all in a String.
     */
    @Override
    public String toString() {
        return ("[" + getIcon() + "]" + "[" + getStatusIcon() + "] "
                + getTaskName() + " (at: " + getDateTime() + ")");
    }
}
