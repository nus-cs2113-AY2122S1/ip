package tan.tasktype;

public class Deadline extends Task {

    private final String deadlineIcon = "D";
    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public Deadline(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns the Task's type in String.
     *
     * @return Returns the string "Deadline".
     */
    @Override
    public String getTaskType() {
        return "Deadline";
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
    @Override
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Returns the whole string
     * including its icon, status, task name
     * and deadline to be printed.
     *
     * @return The task icon, status, name and deadline in String.
     */
    @Override
    public String toString() {
        return ("[" + getIcon() + "]" + "[" + getStatusIcon() + "] "
                + getTaskName() + " (by: " + getDateTime() + ")");
    }
}
