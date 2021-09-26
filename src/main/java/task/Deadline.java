package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a single Deadline task
 * Each event contains a description of the deadline and a status indicating whether
 * it has been marked as done, along with a LocalDateTime representing when it is due
 */
public class Deadline extends Task {
    private LocalDateTime time;
    private static final String dtFormat = "dd/MM/yyyy HHmm";
    protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dtFormat);

    /**
     * Sole constructor for Deadline object
     * @param desc Represents name of object
     * @param timeString Represents user input of when the deadline is due in format of dd/mm/yyyy hhmm
     * @param status Represents whether deadline is marked as done
     * @throws DateTimeParseException Thrown when user input of when deadline is due is in wrong format
     */
    public Deadline (String desc, String timeString, Boolean status) throws DateTimeParseException {
        super(desc, status);
        LocalDateTime time = LocalDateTime.parse(timeString, dtf);
        setTime(time);
    }

    /**
     * Sets when the deadline is due
     * @param time DateTime when deadline is due
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Gets string representation of when deadline is due in dd/mm/yyyy format
     * @return String representation of DateTime
     */
    public String getTime() {
        return time.format(dtf);
    }

    /**
     * Gets the complete string representation of an individual deadline task, meant for user output
     * This method overrides from the parent class
     * @return String Deadline represented by string including type, status, description and when it is due
     */
    @Override
    public String toString() {
        return "[d] [" + getStatus() + "] " + getDescription()
                + " (By: " + getTime() + ")";
    }

    /**
     * Compares the starting date of the deadline, only called from TodayCommand
     * @param ld Date to compare against
     * @return Boolean Whether starting date is same as one specified
     */
    public Boolean compareDate(LocalDate ld) {
        return ld.equals(time.toLocalDate());
    }
}
