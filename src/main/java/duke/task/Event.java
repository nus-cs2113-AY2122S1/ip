package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate eventStartDate;
    private final LocalDate eventEndDate;
    private final String eventStartTime;
    private final String eventEndTime;

    public Event(String eventName, LocalDate eventStartDate, String eventStartTime,
                 LocalDate eventEndDate, String eventEndTime) {
        super(eventName);
        this.eventStartDate = eventStartDate;
        this.eventStartTime = eventStartTime;
        this.eventEndDate = eventEndDate;
        this.eventEndTime = eventEndTime;
    }
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
    @Override
    public String toString() {
        return "[E]" + "[" + this.TaskStatus() + "] " + this.getTaskName()
                + "(at: " + this.getEventDatesTimes(true) + ")";
    }
}