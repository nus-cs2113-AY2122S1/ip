package duke.exceptions;

public class NotFoundException extends Exception {
    public final String NOT_FOUND_RESPONSE = "There are no matching tasks.";

    @Override
    public String getMessage(){
        return NOT_FOUND_RESPONSE;
    }
}
