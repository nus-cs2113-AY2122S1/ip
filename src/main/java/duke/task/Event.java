package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task{

    private LocalDateTime duration;

    public Event(String description, LocalDateTime duration) {
        super(description);
        this.duration = duration;
    }

    public LocalDateTime getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + Parser.printDateAndTimeAsString(duration) + ")";
    }
}
