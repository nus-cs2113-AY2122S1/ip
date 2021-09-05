public class Event extends Task {

    public static final String DESCRIPTION_EMPTY_ERROR_MESSAGE = "The description of a event cannot be empty.";
    public static final String AT_EMPTY_ERROR_MESSAGE = "The time at which of a event should be completed cannot be empty.";
    private String at;

    public Event(String description, String at) {
        super(description);
        if (checkStringNullOrEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERROR_MESSAGE);
        } else if(checkStringNullOrEmpty(at)) {
            throw new IllegalArgumentException(AT_EMPTY_ERROR_MESSAGE);
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
