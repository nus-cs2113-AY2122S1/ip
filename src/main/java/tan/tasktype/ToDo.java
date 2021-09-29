package tan.tasktype;

public class ToDo extends Task {

    private final String toDoIcon = "T";

    public ToDo(String description) {
        super.setTaskName(description);
    }

    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the Task's type in String.
     *
     * @return Returns the string "ToDo".
     */
    @Override
    public String getTaskType() {
        return "ToDo";
    }

    /**
     * Returns "-" for the Todo as it
     * does not have a date.
     *
     * @return Returns the string "-".
     */
    @Override
    public String getDateTimeInString() {
        return "-";
    }

    /**
     * Returns "-" for the Todo as it
     * does not have a date.
     *
     * @return Returns the string "-".
     */
    @Override
    public String getDateTimeForStorage() {
        return "-";
    }

    /**
     * Returns the icon for todo tasks.
     *
     * @return Returns the String "T".
     */
    @Override
    public String getIcon() {
        return toDoIcon;
    }

    /**
     * Returns the whole string
     * including its icon, status & task name to be printed.
     *
     * @return The task icon, status & name in String.
     */
    @Override
    public String toString() {
        return ("[" + getIcon() + "]"
                + "[" + getStatusIcon() + "] " + getTaskDescription());
    }
}
