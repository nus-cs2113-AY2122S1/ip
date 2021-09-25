package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that has a specific time it is scheduled at
 */
public class Event extends Task {

    private final String at;
    private final LocalDateTime atDT;

    /**
     * Constructs an Event with the "at" being a String
     *
     * @param description description of the event
     * @param at          time at which event is at
     * @param isDone      whether the event has been completed or not
     */
    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.atDT = null;
        this.isDone = isDone;
    }

    /**
     * Constructs an Event with the "at" being a LocalDateTime
     *
     * @param description description of the event
     * @param atDT        time at which event is at
     * @param isDone      whether the event has been completed or not
     */
    public Event(String description, LocalDateTime atDT, boolean isDone) {
        super(description);
        this.at = null;
        this.atDT = atDT;
        this.isDone = isDone;
    }

    /**
     * @return the LocalDateTime that the task is due/at if it's a deadline or event
     */
    @Override
    public LocalDateTime getDT() {
        return atDT;
    }

    /**
     * Converts the task into a String fit for being stored into a data file
     *
     * @return String consisting of all the task information to be stored in a data file
     */
    @Override
    public String toData() {
        if (atDT == null) {
            return "E | " + ((isDone) ? 1 : 0) + " | " + task + " | " + at;
        }
        return "E | " + ((isDone) ? 1 : 0) + " | " + task + " | " +
                atDT.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Converts the task into a String to be printed for the user to see
     *
     * @return String consisting of all the task information to be shown to user
     */
    @Override
    public String toString() {
        if (atDT == null) {
            return "[E]" + (isDone ? "[X] " : "[ ] ") + task + " (at: " + at + ")";
        }
        return "[E]" + (isDone ? "[X] " : "[ ] ") + task + " (at: " +
                atDT.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

}
