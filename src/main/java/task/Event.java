package task;

public class Event extends Task {
    protected String atWhen;

    /**
     * Class constructor with the specified task name and
     * when the event is.
     *
     * @param taskName The name of the Event
     * @param atWhen The date and time of the Event
     */
    public Event(String taskName, String atWhen) {
        super(taskName);
        this.atWhen = atWhen;
    }

    /**
     * Class constructor with the specified task name, isDone value and when the event is.
     * Mainly used for creating an Event using values loaded from file.
     *
     * @param taskName The name of the Event
     * @param isDone The boolean value of whether Event is done
     * @param atWhen The date and time of the Event
     */
    public Event(String taskName, boolean isDone, String atWhen) {
        super(taskName, isDone);
        this.atWhen = atWhen;
    }

    /**
     * Returns the date and time of the Event.
     *
     * @return The date and time of the Event
     */
    public String getAtWhen() {
        return atWhen;
    }

    /**
     * Returns the String-formatted Event for printing.
     *
     * @return The string-formatted Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At -> " + atWhen + ")";
    }
}
