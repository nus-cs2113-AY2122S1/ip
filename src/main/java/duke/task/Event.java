package duke.task;

public class Event extends Task {

    public static final String DESCRIPTION_EMPTY_ERROR_MESSAGE = "The description of a event cannot be empty.";
    public static final String AT_EMPTY_ERROR_MESSAGE = "The time at which of a event should be completed cannot be empty.";
    private String at;

    /**
     * Creates an Event Task
     *
     * @param description to describe the event
     * @param at          the time at which the event is at
     * @throws IllegalArgumentException if the description or at is empty or null
     */
    public Event(String description, String at) throws IllegalArgumentException {
        super(description);
        if (checkStringNullOrEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERROR_MESSAGE);
        } else if (checkStringNullOrEmpty(at)) {
            throw new IllegalArgumentException(AT_EMPTY_ERROR_MESSAGE);
        }
        this.at = at;
    }

    @Override
    public String saveToText() {
        return "E | " + super.saveToText() + " | " + at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
