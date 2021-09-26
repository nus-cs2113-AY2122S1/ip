package duke.exceptions;

public class DeleteRangeException extends Exception {
    public final String DELETE_RANGE_RESPONSE =  "That task does not exist.";

    @Override
    public String getMessage(){
        return DELETE_RANGE_RESPONSE;
    }
}
