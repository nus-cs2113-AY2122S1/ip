package duke.manager.task;

import duke.message.Message;
import duke.ui.UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * <h1>Event</h1>
 * This class is a child of <code>Task</code>. It occurs at a specific date and time. As such, an
 * <code>Event</code> object additionally contains a String <code>at</code> to represent when the event occurs at.
 */
public class Event extends Task {

    protected String at;
    protected LocalDateTime atInDateTime;
    protected boolean isInDateTimeFormat = false;
    protected static final String EMPTY_STRING = "";

    public Event(String description, String at) {
        super(description);
        try {
            atInDateTime = LocalDateTime.parse(at, DateTimeFormat.STRING_TO_DATE_TIME_FORMATTER);
            isInDateTimeFormat = true;
        } catch (DateTimeParseException dte) {
            System.out.println(UserInterface.HORIZONTAL_BAR
                    + System.lineSeparator() + Message.NOT_DATE_TIME_MESSAGE);
            // if by is not parsable, store it as a String
            if (at.equals(EMPTY_STRING)) {
                this.at = "???";
            } else {
                this.at = at;
            }
        }
    }

    public String getAt() {
        if (isInDateTimeFormat) {
            return DateTimeFormat.DATE_TIME_TO_STRING_FORMATTER.format(atInDateTime);
        } else {
            return at;
        }
    }

    public void setAt(String at) {
        try {
            atInDateTime = LocalDateTime.parse(at, DateTimeFormat.STRING_TO_DATE_TIME_FORMATTER);
            isInDateTimeFormat = true;
        } catch (DateTimeParseException dte) {
            System.out.println(Message.NOT_DATE_TIME_MESSAGE);
            // if by is not parsable, store it as a String
            if (at.equals(EMPTY_STRING)) {
                this.at = "???";
            } else {
                this.at = at;
            }
        }
    }

    /**
     * Returns the event description with its status in a more reader friendly manner
     */
    @Override
    public String getTaskDescriptionWithStatus() {
        return "[E]" + super.getTaskDescriptionWithStatus() + " (at: " + getAt() + ")";
    }
}
