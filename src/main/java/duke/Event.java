package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An inheritance from task that requires a range of time for additional input
 */
public class Event extends TimedTask {

    public static final char TASK_TYPE_EVENT = 'E';
    public static final int FIRST_TIME_INPUT = 0;
    public static final int SECOND_TIME_INPUT = 1;
    private LocalDateTime endDate;

    /**
     * Constructor of Event from console
     *
     * @param description of the event task
     * @param date        is an array of 2 dates, start_date and end_date respectively
     */
    public Event(String description, LocalDateTime[] date) {
        super(description, TASK_TYPE_EVENT, date[0]);
        endDate = date[1];
    }

    /**
     * Constructor of Event from file
     *
     * @param description of the event task
     * @param date        is an array of 2 dates, start_date and end_date respectively
     */
    public Event(String description, boolean isDone, LocalDateTime[] date) {
        super(description, TASK_TYPE_EVENT, isDone, date[0]);
        endDate = date[1];
    }

    /**
     * Convert event task to string upon printing on console
     *
     * @return string of event task
     */
    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description + " (at: " + getDate() + ")";
    }

    /**
     * Convert datetime object to string for storing in file
     *
     * @return string consist of start_date and end_date
     */
    public String getDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formatStartDateTime = getStartDate().format(format);
        String formatEndDateTime = endDate.format(format);
        return formatStartDateTime + " to " + formatEndDateTime;
    }
}
