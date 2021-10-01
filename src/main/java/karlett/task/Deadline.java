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

    public boolean isOnTheDay(LocalDateTime time) {
        return time.equals(by);
    }

    /**
     * Return a Deadline object, setting its task
     * status to false by default. This constructor
     * is used for user input.
     *
     * @param description details of a deadline task
     * @param by deadline of a deadline task
     */
    public Deadline(String description, LocalDateTime by) throws IOException {
        this.description = description;
        this.isDone = false;
        this.by = by;
    }

    /**
     * Return a Deadline object, setting its task status
     * according to the task status given. This constructor
     * is used for loading file data.
     *
     * @param description details of a deadline task
     * @param by deadline of a deadline task
     * @param isDone task status of the event
     */
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
