package duke.exceptions;

public class AlreadyDoneException extends Exception {
    public final String DONE_RESPONSE = "This task is already completed.";

    @Override
    public String getMessage(){
        return DONE_RESPONSE;
    }
}
