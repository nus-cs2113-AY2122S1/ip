package duke.task;

import duke.DukeUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateTimeString = dateTime.format(formatter);
        return String.format("%s//%s//%s//%s", getTaskType(),getDescription(),dateTimeString,getStatusIcon());
    }
}
