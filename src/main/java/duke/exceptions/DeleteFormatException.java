package duke.exceptions;

public class DeleteFormatException extends Exception {
    public final String DELETE_FORMAT_RESPONSE =  "The description of delete cannot be empty.";

    @Override
    public String getMessage(){
        return DELETE_FORMAT_RESPONSE;
    }
}
