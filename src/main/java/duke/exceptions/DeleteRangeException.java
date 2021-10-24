package duke.exceptions;

//used when task user wants to delete does not exist
public class DeleteRangeException extends Exception {

    public final String DELETE_RANGE_RESPONSE =  "Kao doesn't see that task anywhere ┐( ・∀・ )┌";

    @Override
    public String getMessage(){
        return DELETE_RANGE_RESPONSE;
    }
}
