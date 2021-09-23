package task.subtask;

import ui.Display;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an 'event' type task.
 */
public class Event extends Deadline {

    /**
     * Creates a new 'event' type task.
     *
     * @param taskName Task name provided by user.
     * @param eventDate Date of event provided by user.
     * @param eventTime Time of event provided by user.
     */
    public Event(String taskName, LocalDate eventDate, LocalTime eventTime) {
        super(taskName, eventDate, eventTime);
    }

    /**
     * Displays to the user the status, name and time of the 'event' type task.
     *
     * @return String representation of 'event' type task to be displayed to user.
     */
    @Override
    public String toString() {
        return Display.getTwoCheckboxDisplay(Display.CHECKBOX_EVENT_TASK_TYPE, getIsCompleted())
                + Display.SPACE + getTask() + Display.OPEN_DATE_BRACKET + getDatetime() + Display.CLOSE_DATE_BRACKET;
    }
}
