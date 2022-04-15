package wutdequack.terminator.objects.task;

public class Deadline extends Task{
    protected String datetime;

    /**
     * Create Deadline with given name and associated datetime.
     * @param name The description of the Deadline task.
     * @param datetime The datetime of when the task is due.
     */
    public Deadline(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Get the current due date and time of the Deadline task.
     * @return The string with the datetime.
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Set the current due date and time of the Deadline task.
     * @param datetime The string with the datetime.
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * Get the Item type which is D in this case.
     * @return A string "D".
     */
    @Override
    public String getItemType() {
        return "D";
    }

    /**
     * Changes a Task into a human-readable format.
     * @return A formatted String of the Deadline, its status, type, description and datetime.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), datetime);
    }

    /**
     * Changes a Task into a human-readable format to be stored in file.
     * @return A formatted String of the Deadline, its status, type, description and datetime.
     */
    @Override
    public String toFileStringFormat() {
        return String.format("%s | %s", super.toFileStringFormat(), datetime);
    }
}
