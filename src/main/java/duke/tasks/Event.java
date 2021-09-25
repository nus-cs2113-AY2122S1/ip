package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    protected String at;
    protected LocalDate eventDate;
    protected LocalTime eventTime;
    protected LocalDateTime eventDateTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        if (isValidDateTimeFormat(at, DATE_FORMAT)) {
            this.eventDate = LocalDate.parse(at, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } else if (isValidDateTimeFormat(at, TIME_FORMAT)) {
            this.eventTime = LocalTime.parse(at, DateTimeFormatter.ofPattern(TIME_FORMAT));
        } else if (isValidDateTimeFormat(at, DATETIME_FORMAT)) {
            this.eventDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        }
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        if (eventDate != null) {
            return "[E]" + super.toString() + " (at: " + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else if (eventTime != null) {
            return "[E]" + super.toString() + " (at: " + eventTime.format(DateTimeFormatter.ofPattern("HH.mm")) + ")";
        } else if (eventDateTime != null) {
            return "[E]" + super.toString() + " (at: " + eventDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH.mm")) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    /**
     * Checks if the format of the date, time, or dateTime the user entered is correct based on a specified format
     *
     * @param value the date/time/dateTime input to be checked
     * @param format the specified format to be checked with
     * @return returns true if the format entered by the user matches the format specified, false otherwise
     */
    private boolean isValidDateTimeFormat(String value, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        try {
            LocalDateTime ldt = LocalDateTime.parse(value, formatter);
            String result = ldt.format(formatter);
            return result.equals(value);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate ld = LocalDate.parse(value, formatter);
                String result = ld.format(formatter);
                return result.equals(value);
            } catch (DateTimeParseException e2) {
                try {
                    LocalTime lt = LocalTime.parse(value, formatter);
                    String result = lt.format(formatter);
                    return result.equals(value);
                } catch (DateTimeParseException e3) {

                }
            }
        }
        return false;
    }
}
