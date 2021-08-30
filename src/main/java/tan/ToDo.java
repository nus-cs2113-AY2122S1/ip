package tan;
public class ToDo extends Task {

    public ToDo(String description) {
        super.setTaskName(description);
    }

    /**
     * Returns the icon for todo tasks.
     *
     * @return Returns the String "T"
     */
    public String getIcon() {
        return "T";
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
