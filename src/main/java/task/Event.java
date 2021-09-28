package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Represents an event which has specific date and time which is scheduled at.
 */
public class Event extends Task {
    /** Event date and time */
    protected LocalDateTime eventDateTime;

    /**
     * Constructs a new event task.
     * @param description Event task description
     * @param eventDateTime Date and time details of the event task
     */
    public Event(String description, LocalDateTime eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    /**
     * Converts event task object into a String, which is used to store it in the file.
     * @return String consisting of all the information related to the task
     */
    @Override
    public String toFileFormat() {
        return "E # " + (isDone ? "1" : "0") + " # " + description + " # " +
                eventDateTime + "\n";
    }

    /**
     * Converts event task object into a String, which is used to display in the console.
     * @return String consisting of all the information related to the task
     */
    @Override
    public String toString() {
        return "[E] [" + getStatus() + "] " + getDescription() + " (at: " +
                eventDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + ")";
    }

    /**
     * @return The date and time details of the event task.
     */
    @Override
    public LocalDateTime getDT() {
        return eventDateTime;
    }

}