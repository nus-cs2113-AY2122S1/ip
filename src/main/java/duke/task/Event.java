package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDate eventStartDate;
    private final LocalDate eventEndDate;
    private final String eventStartTime;
    private final String eventEndTime;

    /**
     * Event task constructor.
     * @param eventName the name/description of the event task
     * @param eventStartDate the starting date of the event task
     * @param eventStartTime the starting time of the event task
     * @param eventEndDate the ending date of the event task
     * @param eventEndTime the ending time of the event task
     */
    public Event(String eventName, LocalDate eventStartDate, String eventStartTime,
                 LocalDate eventEndDate, String eventEndTime) {
        super(eventName);
        this.eventStartDate = eventStartDate;
        this.eventStartTime = eventStartTime;
        this.eventEndDate = eventEndDate;
        this.eventEndTime = eventEndTime;
    }

    /**
     * Get the starting and ending date and time of the event task.
     * @param isNewFormat whether use a new date format(different from the user old format)
     * @return the starting/ending date and time of the event task in the new/old format
     */
    public String getEventDatesTimes(boolean isNewFormat) {
        int minutesStartIndex = 0;
        int minutesEndIndex = 2;
        int hoursStartIndex = 2;
        String eventDatesTimes;
        if (isNewFormat) {
            eventDatesTimes = this.eventStartDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    + " "
                    + this.eventStartTime.substring(minutesStartIndex,minutesEndIndex)
                    + ":"
                    + this.eventStartTime.substring(hoursStartIndex)
                    + " to "
                    + this.eventEndDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    + " "
                    + this.eventEndTime.substring(minutesStartIndex,minutesEndIndex)
                    + ":"
                    + this.eventEndTime.substring(hoursStartIndex);
        } else {
            eventDatesTimes = this.eventStartDate.toString()
                    + " "
                    + this.eventStartTime.substring(minutesStartIndex,minutesEndIndex)
                    + this.eventStartTime.substring(hoursStartIndex)
                    + " to "
                    + this.eventEndDate.toString()
                    + " "
                    + this.eventEndTime.substring(minutesStartIndex,minutesEndIndex)
                    + this.eventEndTime.substring(hoursStartIndex);
        }
        return eventDatesTimes;
    }

    /**
     * Show the full information of the event task.
     * @return the full information of the event task as String
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getTaskStatusInString() + "] " + this.getTaskName()
                + "(at: " + this.getEventDatesTimes(true) + ")";
    }
}