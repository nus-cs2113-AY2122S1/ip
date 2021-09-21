package duke.manager.task;

import duke.manager.task.formatter.DateTimeFormat;
import duke.message.Message;
import duke.ui.UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * <h1>Deadline</h1>
 * This class is a child of <code>Task</code>. It is a more specific type of task that needs to be done
 * by a certain date and time. As such, a <code>Deadline</code> object additionally contains a String <code>by</code>
 * to represent when the deadline of the task is.
 */
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
            if (by.equals(EMPTY_STRING)) {
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

    /**
     * Returns the deadline description with its status in a more reader friendly manner
     */
    @Override
    public String getTaskDescriptionWithStatus() {
        return "[D]" + super.getTaskDescriptionWithStatus() + " (by: " + getBy() + ")";
    }
}
