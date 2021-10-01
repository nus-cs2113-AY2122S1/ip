package herrekt.taskmanager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline<T> extends Task {
    protected T date;

    /**
     * Initialise a Deadline with a description and date.
     *
     * @param description Description of the deadline
     * @param date The date can be either a String or LocalDate class.
     */
    public Deadline(String description, T date) {
        super(description);
        this.date = date;
    }

    protected String getDate() {
        if (date instanceof String) {
            return (String) date;
        } else if (date instanceof LocalDateTime) {
            String month = ((LocalDateTime) date).getMonth().toString().substring(0,3);
            int day = ((LocalDateTime) date).getDayOfMonth();
            int year = ((LocalDateTime) date).getYear();
            int hour = ((LocalDateTime) date).getHour();
            String amOrPm = "am";
            if (hour > 12) {
                hour -= 12;
                amOrPm = "pm";
            }
            int minute = ((LocalDateTime) date).getMinute();
            return month + " " + day + " " + year + " " + hour + ":" + minute + amOrPm;
        } else {
            String month = ((LocalDate) date).getMonth().toString().substring(0,3);
            int day = ((LocalDate) date).getDayOfMonth();
            int year = ((LocalDate) date).getYear();
            return month + " " + day + " " + year;
        }
    }

    /**
     * Returns the save format of the current Deadline.
     * Converts the deadline into a String format recognizable in the save file.
     *
     * @return The deadline as a string.
     */
    @Override
    protected String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "D" + SAVE_FILE_SPACER
                + done + SAVE_FILE_SPACER
                + this.description + SAVE_FILE_SPACER
                + this.date.toString();
    }

    protected String getDescription() {
        return super.description + " (by: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
