package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a single Event task
 * Each event contains a description of the event and a status indicating whether
 * it has been marked as done, along with two LocalDateTime representing the
 * starting and ending times of the event
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final String dtFormat = "dd/MM/yyyy HHmm";
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dtFormat);

    /**
     * Sole constructor for Event object
     * @param desc Represents name of object
     * @param startString Represents user input of starting time in format of dd/mm/yyyy hhmm
     * @param endString Represents user input of ending time in format of dd/mm/yyyy hhmm
     * @param status Represents whether event is marked as done
     * @throws DateTimeParseException Thrown when user input of starting or ending time is in a wrong format
     */
    public Event(String desc, String startString, String endString, Boolean status) throws DateTimeParseException {
        super(desc, status);
        LocalDateTime start = LocalDateTime.parse(startString, dtf);
        LocalDateTime end = LocalDateTime.parse(endString, dtf);
        setTime(start, end);
    }

    /**
     * Sets the start and ending times of event
     * @param start DateTime indicating start of event
     * @param end DateTime indicating end of event
     */
    public void setTime(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Gets string representation of starting DateTime of event in dd/mm/yyyy hhmm format
     * @return String representation of DateTime
     */
    public String getStart() {
        return start.format(dtf);
    }

    /**
     * Gets string representation of ending DateTime of event
     * @return String representation of DateTime
     */
    public String getEnd() {
        return end.format(dtf);
    }

    /**
     * Gets the complete string representation of an individual Event task, meant for user output.
     * This method overrides from the parent class
     * @return String Event represented by string including type, status, description, start and end times
     */
    public String toString() {
        return "[e] [" + getStatus() + "] " + getDescription()
                + " (" + getStart() + " to " + getEnd() + ")";
    }

    /**
     * Compares the starting date of the event, only called from TodayCommand
     * @param ld Date to compare against
     * @return Boolean Whether starting date is same as one specified
     */
    public Boolean compareDate(LocalDate ld) {
        return ld .equals(start.toLocalDate());
    }
}
