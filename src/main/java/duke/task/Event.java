package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

public class Event extends Task {
    private static final String EVENT_TIME_KEYWORD = " /at";
    
    private String eventTime;

    /**
     * Constructor of event objects by first initializing a task object
     * then the eventTime of this object.
     * 
     * @param description task description from user's input, containing the task
     *                    description and event time.
     * @throws DukeInvalidAddTaskException if task description contains "--", which
     * is a format reserved for only when saving the task to the text file.
     */
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
