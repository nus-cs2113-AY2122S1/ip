package duke.task;

import duke.DukeUI;
import duke.exception.DukeDateTimeFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task with an additional date-time
 */
public abstract class TimedTask extends Task{
    protected LocalDateTime dateTime;

    /**
     * Initializes an instance of a timed task
     *
     * @param description the general task description
     * @param dateTime the date and time of the task
     * @throws DukeDateTimeFormatException when the date and time input is in wrong formats
     */
    public TimedTask(String description, String dateTime)  throws DukeDateTimeFormatException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.dateTime = LocalDateTime.parse(dateTime, formatter);
        } catch(DateTimeParseException e) {
            throw new DukeDateTimeFormatException();
        }
    }

    /**
     * Get the date data of the current task
     *
     * @return The date data of the task
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Parse the task data into a string to store in a text file
     * with addition date-time information
     *
     * @return The string to store in the text file
     */
    @Override
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateTimeString = dateTime.format(formatter);
        return String.format("%s//%s//%s//%s", getTaskType(),getDescription(),dateTimeString,getStatusIcon());
    }
}
