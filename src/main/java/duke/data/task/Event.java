package duke.data.task;

import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used for tasks that start at a specific date and time
 * E.g: team meeting at 23/12/2012 2pm
 */
public class Event extends Task {
    protected LocalDateTime dateAndTime;

    public Event(String description, LocalDateTime dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    public Event(String description, LocalDateTime dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    public String getFormattedDateAndTime() {
        return this.dateAndTime.format(DateTimeFormatter.ofPattern(Ui.DATE_TIME_FORMAT_TO_PRINT));
    }


    /**
     * Returns Event task formatted for application UI,  in the form "[E][ ] description (at: date/time)"
     *
     * @return Formatted Event task string
     */
    @Override
    public String toString() {
        return EVENT_LOGO + super.toString() + " (at: " + getFormattedDateAndTime() + ")";
    }

    /**
     * Returns Event task formatted for data file in the form "E | 1/0 | description | dateAndTime"
     *
     * @return Formatted Event task string for data file
     */
    @Override
    public String toTextFileString() {
        return EVENT_ACRONYM + " | " + super.toTextFileString() + " | " + dateAndTime;
    }
}
