package unker.task;

import java.time.LocalDate;
import java.time.LocalTime;
import unker.util.StringUtil;

public class Event extends Task {

    public static final String EVENT_DATA_PATTERN = "^(.+) /[aA][tT] "
            + "([\\d]{4}-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:0[1-9])|(?:[1-2]\\d)|(?:3[01]))) " // Validates Date 
            + "((?:(?:[0-1]\\d)|(?:2[0-3])):[0-5]?\\d(?::[0-5]?\\d)?)-"
            + "((?:(?:[0-1]\\d)|(?:2[0-3])):[0-5]?\\d(?::[0-5]?\\d)?)$";
    protected LocalDate date;
    protected LocalTime fromTime;
    protected LocalTime toTime;

    public Event(String task, LocalDate date, LocalTime fromTime, LocalTime toTime) {
        super(task);
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Gets the date of the event.
     * 
     * @return The date of the event.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the start time of the event.
     * 
     * @return The start time of the event.
     */
    public LocalTime getFromTime() {
        return fromTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalTime getToTime() {
        return toTime;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s, %s to %s)", super.toString(), StringUtil.toFriendlyDateString(this.date),
                StringUtil.toFriendlyTimeString(this.fromTime), StringUtil.toFriendlyTimeString(this.toTime));
    }
    
    @Override
    public String getSaveableString() {
        return String.format("%s,%d,%s /at %s %s-%s", "E", isDone ? 1 : 0, description, 
                StringUtil.toISODateString(this.date), StringUtil.toISOTimeString(this.fromTime),
                StringUtil.toISOTimeString(this.toTime));
    }
}
