package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private static final String DESCRIPTION_EMPTY_ERROR_MESSAGE = "The description of "
            + "a event cannot be empty.";
    private static final String AT_EMPTY_ERROR_MESSAGE = "The time at which of a "
            + "event should be completed cannot be"
            + " empty.";
    private DateTimeFormatter formatter;

    protected LocalDateTime at;

    /**
     * Creates an Event Task
     *
     * @param description to describe the event
     * @param at          the time at which the event is at
     * @throws IllegalArgumentException if the description or at is empty or null
     */
    public Event(String description, String at)
            throws IllegalArgumentException, DateTimeParseException {
        super(description);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (isStringNullOrEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERROR_MESSAGE);
        } else if (isStringNullOrEmpty(at)) {
            throw new IllegalArgumentException(AT_EMPTY_ERROR_MESSAGE);
        }

        this.at = LocalDateTime.parse(at,formatter);
    }

    @Override
    public String saveToText() {
        return "E | " + super.saveToText() + " | " + at.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at.format(formatter));
    }
}
