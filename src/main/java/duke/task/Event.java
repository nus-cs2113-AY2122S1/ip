package duke.task;

import duke.exceptions.EmptyField;

public class Event extends Task{
    public String symbolSetTime = "/at";
    protected static String SYMBOL = "E";

    /**
     * Convenience constructor using raw values.
     *
     * @param description description of the Event
     * @param time start time of the Event
     * @throws EmptyField if one or more param is missing or of wrong format
     */
    public Event(String description, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
    }

    /**
     * Convenience constructor using raw values.
     *
     * @param description description of the Event
     * @param done status of the Event
     * @param time start time of the Event
     * @throws EmptyField if one or more param is missing or of wrong format
     */
    public Event(String description, boolean done, String time) throws EmptyField {
        setDescription(description);
        setTime(time);
        setStatus(done);
    }

    @Override
    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String getSaveFormat() {
        return super.getSaveFormat(getTime());
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public void setTime(String time) throws EmptyField {
        if (time.isBlank()) {
            throw new EmptyField();
        }
        this.time = time;
    }

    /**
     * Returns all information of the Event as a user-friendly String format
     *
     * @return all information about the Event
     */
    @Override
    public String toString() {
        String classIndicator = this.getClass().getSimpleName().substring(0,1);
        String statusIndicator = status ? "X" : " ";
        return String.format("[%s][%s] %s (at: %s)", classIndicator, statusIndicator, description, time);
    }
}
