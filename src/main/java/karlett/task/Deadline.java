package karlett.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    public String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return by.format(formatter);
    }

    /* constructor used for user input */
    public Deadline(String description, LocalDateTime by) throws IOException {
        this.description = description;
        this.isDone = false;
        this.by = by;
    }

    /* constructor used for loading file data */
    public Deadline(String description, LocalDateTime by, boolean isDone) throws IOException {
        this.description = description;
        this.isDone = isDone;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] "
                + this.getDescription()
                + " (by: " + this.getBy() + ")";
    }
}
