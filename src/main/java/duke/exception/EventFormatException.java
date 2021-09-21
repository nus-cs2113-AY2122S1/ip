package duke.exception;

public class EventFormatException extends Exception {

    private final String EVENT_INCORRECT_FORMAT_MSG = "=> Yikes, your event command is wrong!"
            + " Please follow the format:\n"
            + "   [\uD83D\uDCAC] 6. Add Events -> {event <task description> /at <task date&time>}";

    @Override
    public String toString() {
        return EVENT_INCORRECT_FORMAT_MSG;
    }

}
