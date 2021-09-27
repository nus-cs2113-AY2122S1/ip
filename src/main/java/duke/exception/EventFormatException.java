package duke.exception;

/**
 * Represents an exception where the user's Event command does not comply with the correct format.
 * Event command correct format: event [task description] /at [task date&time].
 */
public class EventFormatException extends Exception {

    private final String EVENT_INCORRECT_FORMAT_MSG = "=> Yikes, your event command is wrong!"
            + " Please follow the format:\n"
            + "   [?] 6. Add Events -> {event <task description> /at <task date&time>}";

    @Override
    public String toString() {
        return EVENT_INCORRECT_FORMAT_MSG;
    }

}
