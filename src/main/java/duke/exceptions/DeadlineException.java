package duke.exceptions;

public class DeadlineException extends Exception {
    public final String DEADLINE_RESPONSE = "The description of deadline cannot be empty.";

    @Override
    public String getMessage() {
        return DEADLINE_RESPONSE;
    }
}
