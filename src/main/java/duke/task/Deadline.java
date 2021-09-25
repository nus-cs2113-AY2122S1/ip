package duke.task;

import duke.command.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String getType(){
        return ("D");
    }

    @Override
    public String getDescription() {
        return (description + "(by: " + Parser.dateStringOutput(dateTime) + ")");
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String getOriginalDescription() {
        return (description + "/by " + dateTime.format(Parser.inputFormatter));
    }


}
