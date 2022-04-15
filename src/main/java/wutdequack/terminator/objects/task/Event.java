package wutdequack.terminator.objects.task;

public class Event extends Task{
    protected String datetime;

    /**
     * Create Event with given name and associated datetime.
     * @param name The description of the Event task.
     * @param datetime The datetime of when the task is due.
     */
    public Event(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    /**
     * Get the current due date and time of the Event task.
     * @return The string with the datetime.
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Set the current due date and time of the Event task.
     * @param datetime The string with the datetime.
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * Get the Item type which is E in this case.
     * @return A string "E".
     */
    @Override
    public String getItemType() {
        return "E";
    }

    /**
     * Changes a Task into a human-readable format.
     * @return A formatted String of the Event, its status, type, description and datetime.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), datetime);
    }

    /**
     * Changes a Task into a human-readable format to be stored in file.
     * @return A formatted String of the Event, its status, type, description and datetime.
     */
    @Override
    public String toFileStringFormat() {
        return String.format("%s | %s", super.toFileStringFormat(), datetime);
    }
}
