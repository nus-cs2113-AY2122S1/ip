package Duke.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Subclass for Deadline objects under tasks.
 */


public class Deadline extends Task{
    private String when = "0000";
    private String originalWhen;

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Deadline class
     *
     * @param description The name of the task
     * @param when The date and time in which the task is due by
     */

    public Deadline(String description, String when) {
        super(description);
        this.when = when.contains(":") ? when.substring(when.indexOf(":") + 1) : this.when;
        this.date = LocalDate.parse(when.substring(4,when.indexOf(":")));
        this.time = getTime(this.when);
        this.originalWhen = when;
    }

    /**
     * Returns the time in hours and minutes from a string of 4 numbers
     *
     * @param time The string of 4 numbers that represent the 24 hour clock time
     * @return Returns the time in hours and minutes in a 12 hour clock time
     */

    public LocalTime getTime(String time) {
        String hourString = time.substring(0,2);
        String minuteString = time.substring(2);
        int hour = Integer.parseInt(hourString);
        int minute = Integer.parseInt(minuteString);
        return LocalTime.of(hour, minute);
    }

    @Override
    public String getDescription() {
        return description + " (By: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";//"%s (By:%s)", description, when);
    }
    @Override
    public String getTaskIcon() {
        return "D";
    }
    @Override
    public String getOriginalDescription() {
        return this.description + " " + this.originalWhen;
    }
}
