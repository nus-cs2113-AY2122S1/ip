package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

public class Event extends Task {
    private static final String EVENT_TIME_KEYWORD = " /at";
    
    private String eventTime;

    public Event(String description) throws DukeInvalidAddTaskException {
        super(Parser.getDescription(description, EVENT_TIME_KEYWORD));
        this.eventTime = Parser.getTime(description, EVENT_TIME_KEYWORD);
    }

    public String getEventTime() {
        return eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}
