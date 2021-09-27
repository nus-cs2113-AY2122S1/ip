package duke.exceptions;

//used when task user wants to complete does not exist
public class DoneRangeException extends Exception {

    public final String DONE_RANGE_RESPONSE =  "That task does not exist.";

    @Override
    public String getMessage(){
        return DONE_RANGE_RESPONSE;
    }
}
