package kate.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate timeFrame;
    private static final String EVENT_CHECKBOX = "[E]";

    public Event(String description, String timeFrame) {
        this.description = description;
        this.isDone = false;
        this.timeFrame = LocalDate.parse(timeFrame);
    }

    public Event(String description, boolean isDone, String timeFrame) {
        this.description = description;
        this.isDone = isDone;
        this.timeFrame = LocalDate.parse(timeFrame);
    }

    /**
     * Formats the date into MMM d yyyy
     *
     * @return Formatted date in String
     */
    public String getFormattedDate() {
        return timeFrame.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Retrieves the event information to be written to a file
     *
     * @return Formatted String of Event information
     */
    public String getTaskInfoForFile() {
        return "E" + " | " + isDone + " | " + description + " | " + timeFrame;
    }

    /**
     * Retrieves Task information and additional event information
     *
     * @return String description of task and additional event information
     */
    @Override
    public String getTaskInfo() {
        return EVENT_CHECKBOX + super.getTaskInfo()
                + " (at: " + getFormattedDate() + ")";
    }
}
