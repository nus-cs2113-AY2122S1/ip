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

    /* constructor used for user input */
    public Event(String description, LocalDateTime at) throws IOException {
        this.description = description;
        this.isDone = false;
        this.at = at;
    }

    /* constructor used for loading file data */
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
}
