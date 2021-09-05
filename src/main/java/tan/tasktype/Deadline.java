package tan.tasktype;

public class Deadline extends Task {

    private final String deadlineIcon = "D";
    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the icon for Deadline tasks.
     *
     * @return Returns the String "D"
     */
    @Override
    public String getIcon() {
        return deadlineIcon;
    }

    /**
     * Returns the deadline for the task
     * in String.
     *
     * @return Returns deadline in String.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * This will return the whole string
     * including its icon, status, task name
     * and deadline.
     *
     * @return The task icon, status, name and deadline in String.
     */
    @Override
    public String toString() {
        return ("[" + getIcon() + "]" + "[" + getStatusIcon() + "] "
                + getTaskName() + " (by: " + getDateTime() + ")");
    }
}
