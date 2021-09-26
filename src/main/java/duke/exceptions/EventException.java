package duke.exceptions;

public class EventException extends Exception {
    public final String EVENT_RESPONSE = "The description of event cannot be empty.";

    @Override
    public String getMessage(){
        return EVENT_RESPONSE;
    }
}
