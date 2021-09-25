package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    protected String by;
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;
    protected LocalDateTime deadlineDateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (isValidDateTimeFormat(by, DATE_FORMAT)) {
            this.deadlineDate = LocalDate.parse(by, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } else if (isValidDateTimeFormat(by, TIME_FORMAT)) {
            this.deadlineTime = LocalTime.parse(by, DateTimeFormatter.ofPattern(TIME_FORMAT));
        } else if (isValidDateTimeFormat(by, DATETIME_FORMAT)) {
            this.deadlineDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        }
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        if (deadlineDate != null) {
            return "[D]" + super.toString() + " (by: " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else if (deadlineTime != null) {
            return "[D]" + super.toString() + " (by: " + deadlineTime.format(DateTimeFormatter.ofPattern("HH.mm")) + ")";
        } else if (deadlineDateTime != null) {
            return "[D]" + super.toString() + " (by: " + deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH.mm")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    // @@author MadProgrammer-reused
    // Reused from https://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or/20232680
    // with minor modifications
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
    // @@author
}
