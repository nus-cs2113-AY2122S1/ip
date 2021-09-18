package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static final String EVENT_TIME_KEYWORD = " /at";
    
    private LocalDate eventTime;

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
