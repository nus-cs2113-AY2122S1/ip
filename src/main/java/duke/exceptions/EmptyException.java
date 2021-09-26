package duke.exceptions;

public class EmptyException extends Exception {
    public final String EMPTY_RESPONSE = "Command is empty.";

    @Override
    public String getMessage(){
        return EMPTY_RESPONSE;
    }
}
