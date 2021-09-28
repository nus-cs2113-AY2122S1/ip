package karlett.task;

import java.io.IOException;

public class Deadline extends Task {

    public String getBy() {
        return by;
    }

    protected String by;

    /**
     * Return a Deadline object, setting its task
     * status to false by default. This constructor
     * is used for user input.
     *
     * @param description details of a deadline task
     * @param by deadline of a deadline task
     */
    public Deadline(String description, String by) throws IOException {
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
    public Deadline(String description, String by, boolean isDone) throws IOException {
        this.description = description;
        this.isDone = isDone;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + by + ")";
    }
}
