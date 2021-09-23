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

    /**
    private boolean isDate(String args) {
        if (!args.contains("-")) {
            return false;
        } else {
            String[] dateArray = args.split("-");
            if (dateArray.length < 3) {
                return false;
            }
            if (!(dateArray[0].length() == 2 && dateArray[1].length() == 2 && dateArray[2].length() == 4)) {
                return false;
            }
        }
        return true;
    }

    private boolean isTime(String args) {
        char[] chars = args.toCharArray();
        if (chars.length == 4) {
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDateTime(String args) {
        if (!(args.length() == 2)) {
            return false;
        } else {
            String[] dateTimeArray = args.split(" ");
            String dateString = dateTimeArray[0];
            String timeString = dateTimeArray[1];
            if (!(isDate(dateString)) || !(isTime(timeString))) {
                return false;
            }
        }
        return true;
    }
     **/
}
