package duke.task;

import duke.command.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String getType() {
        return ("E");
    }

    @Override
    public String getDescription() {
        return (description + "(at: " + Parser.dateStringOutput(dateTime) + ")");
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String getOriginalDescription() {
        return (description + "/at " + dateTime.format(Parser.inputFormatter));
    }

}
