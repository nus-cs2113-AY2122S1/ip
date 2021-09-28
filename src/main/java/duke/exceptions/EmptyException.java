package duke.exceptions;

//used when user did not input a command
public class EmptyException extends Exception {

    public final String EMPTY_RESPONSE = "Did you call Kao? (・_・  )";

    @Override
    public String getMessage(){
        return EMPTY_RESPONSE;
    }
}
