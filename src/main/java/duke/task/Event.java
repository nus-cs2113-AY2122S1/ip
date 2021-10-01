package duke.task;

public class Event extends Todo {
    String time;

    /**
     * Returns an Event object.
     *
     * @param name Name of the event.
     * @param time Time of the event.
     */
    public Event(String name, String time) {
        super(name);
        setTime(time);
    }

    /**
     * Returns the time of the event.
     *
     * @return Time of the event.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the event.
     *
     * @param time Time of the event.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns a formatted string which contains information about the event.
     *
     * @return Formatted string which contins information about the event.
     */
    @Override
    public String toString() {
        String boolString = super.getIsDone() ? "X" : " ";
        return String.format("[E][%s] %s (at: %s)", boolString, super.getName(), time);
    }


}
