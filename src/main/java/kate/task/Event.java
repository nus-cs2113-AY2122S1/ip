package kate.task;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected int duration;
    private static final String EVENT_CHECKBOX = "[E]";

    public Event(String description, LocalDate date, LocalTime time, int duration) {
        this.description = description;
        this.isDone = false;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    public Event(String description, boolean isDone, LocalDate date, LocalTime time, int duration) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    /**
     * Formats the date into MMM d yyyy
     *
     * @return Formatted date in String
     */
    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Formats the time into HH:mm
     *
     * @return Formatted date in String
     */
    public String getFormattedTime() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Retrieves the event information to be written to a file
     *
     * @return Formatted String of Event information
     */
    public String getTaskInfoForFile() {
        return "E" + " | " + isDone + " | " + description + " | " + date + " | " + time + " | " + duration;
    }

    /**
     * Prints the time frame with added information if user provided a specific duration
     *
     * @return Description of time frame depending on duration specified
     */
    private String printTimeFrame() {
        String timeFrame = "";
        boolean hasDuration = duration > 0;
        if (hasDuration) {
            Duration durationTaken = Duration.of(duration, ChronoUnit.HOURS);
            LocalDateTime startDate = LocalDateTime.of(date, time);
            LocalDateTime endDate = startDate.plus(durationTaken);
            timeFrame = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                    + " to " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        } else {
            timeFrame = getFormattedDate() + " " + getFormattedTime();
        }
        return timeFrame;
    }

    /**
     * Retrieves Task information and additional event information
     *
     * @return String description of task and additional event information
     */
    @Override
    public String getTaskInfo() {
        return EVENT_CHECKBOX + super.getTaskInfo()
                + " (at: " + printTimeFrame() + ")";
    }


}
