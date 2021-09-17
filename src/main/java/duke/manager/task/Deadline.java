package duke.manager.task;

import duke.message.Message;
import duke.ui.UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime byInDateTime;
    protected boolean isInDateTimeFormat = false;
    protected static final String EMPTY_STRING = "";

    public Deadline(String description, String by) {
        super(description);
        try {
            byInDateTime = LocalDateTime.parse(by, DateTimeFormat.STRING_TO_DATE_TIME_FORMATTER);
            isInDateTimeFormat = true;
        } catch (DateTimeParseException dte) {
            System.out.println(UserInterface.HORIZONTAL_BAR
                    + System.lineSeparator() + Message.NOT_DATE_TIME_MESSAGE);
            // if by is not parsable, store it as a String
            if (by.equals("")) {
                this.by = "???";
            } else {
                this.by = by;
            }
        }
    }

    public String getBy() {
        if (isInDateTimeFormat) {
            return DateTimeFormat.DATE_TIME_TO_STRING_FORMATTER.format(byInDateTime);
        } else {
            return by;
        }
    }

    public void setBy(String by) {
        try {
            byInDateTime = LocalDateTime.parse(by, DateTimeFormat.STRING_TO_DATE_TIME_FORMATTER);
            isInDateTimeFormat = true;
        } catch (DateTimeParseException dte) {
            System.out.println(Message.NOT_DATE_TIME_MESSAGE);
            // if by is not parsable, store it as a String
            if (by.equals(EMPTY_STRING)) {
                this.by = "???";
            } else {
                this.by = by;
            }
        }
    }

    @Override
    public String getTaskDescriptionWithStatus() {
        return "[D]" + super.getTaskDescriptionWithStatus() + " (by: " + getBy() + ")";
    }
}
