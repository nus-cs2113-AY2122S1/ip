package duke.exceptions;

//used when user did not input a command
public class EmptyException extends Exception {

    public final String EMPTY_RESPONSE = "Command is empty.";

    @Override
    public String getMessage(){
        return EMPTY_RESPONSE;
    }
}
