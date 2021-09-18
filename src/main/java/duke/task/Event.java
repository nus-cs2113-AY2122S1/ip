package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static final String EVENT_TIME_KEYWORD = " /at";
    
    private LocalDate eventTime;

    public Event(String description) throws DukeInvalidAddTaskException {
        super(Parser.getDescription(description, EVENT_TIME_KEYWORD));
        try {
            this.eventTime = Parser.getTime(description, EVENT_TIME_KEYWORD);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidAddTaskException();
        }    
    }

    public LocalDate getEventTime() {
        return eventTime;
    }

    public String toString() {
        String formattedDate = eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
