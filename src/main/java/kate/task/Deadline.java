package kate.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;
    private static final String DEADLINE_CHECKBOX = "[D]";

    public Deadline(String description, LocalDate date, LocalTime time) {
        this.description = description;
        this.isDone = false;
        this.date = date;
        this.time = time;
    }

    public Deadline(String description, boolean isDone, LocalDate date, LocalTime time) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
        this.time = time;
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
     * Retrieves the deadline information to be written to a file
     *
     * @return Formatted String of Deadline information
     */
    public String getTaskInfoForFile() {
        return "D" + " | " + isDone + " | " + description + " | " + date + " | " + time;
    }

    /**
     * Retrieves Task information and additional deadline information
     *
     * @return String description of task and additional deadline information
     */
    @Override
    public String getTaskInfo() {
        return DEADLINE_CHECKBOX + super.getTaskInfo()
                + " (by: " + getFormattedDate() + " " + getFormattedTime() + ")";
    }
}
