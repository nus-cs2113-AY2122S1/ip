package duke.task;

import duke.DukeUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task with an additional date-time
 */
public abstract class TimedTask extends Task{
    protected LocalDateTime dateTime;

    public TimedTask(String description, String dateTime) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.dateTime = LocalDateTime.parse(dateTime, formatter);
        } catch(DateTimeParseException e) {
            DukeUI.printError(e);
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
