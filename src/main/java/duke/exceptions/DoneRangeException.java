package duke.exceptions;

//used when task user wants to complete does not exist
public class DoneRangeException extends Exception {

    public final String DONE_RANGE_RESPONSE =  "Kao doesn't see that task (  ･д･)?";

    @Override
    public String getMessage(){
        return DONE_RANGE_RESPONSE;
    }
}
