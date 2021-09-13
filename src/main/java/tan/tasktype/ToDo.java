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
     * @return Returns "-".
     */
    @Override
    public String getDateTime() {
        return "-";
    }

    /**
     * Returns the icon for todo tasks.
     *
     * @return Returns the String "T"
     */
    @Override
    public String getIcon() {
        return toDoIcon;
    }

    /**
     * This will return the whole string
     * including its icon, status & task name.
     *
     * @return The task icon, status & name in String.
     */
    @Override
    public String toString() {
        return ("[" + getIcon() + "]"
                + "[" + getStatusIcon() + "] " + getTaskName());
    }
}
