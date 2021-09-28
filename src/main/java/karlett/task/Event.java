package karlett.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;

    public String getAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return at.format(formatter);
    }

    /**
     * Return an event object, setting its task
     * status to false by default. This constructor
     * is used for user input.
     *
     * @param description details of an event
     * @param at time of the event
     */
    public Event(String description, LocalDateTime at) throws IOException {
        this.description = description;
        this.isDone = false;
        this.at = at;
    }

    /**
     * Return an Event object, setting its task status
     * according to the task status given. This constructor
     * is used for loading file data.
     *
     * @param description details of an event
     * @param at time of the event
     * @param isDone task status of the event
     */
    public Event(String description, LocalDateTime at, boolean isDone) throws IOException {
        this.description = description;
        this.isDone = isDone;
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] "
                + this.getDescription()
                + " (at: " + this.getAt() + ")";
    }

    public boolean isOnTheDay(LocalDateTime time) {
        return time.equals(at);
    }
}
