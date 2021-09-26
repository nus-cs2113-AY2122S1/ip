package duke.exceptions;

public class DoneFormatException extends Exception {
    public final String DONE_FORMAT_RESPONSE =  "The description of done cannot be empty.";

    @Override
    public String getMessage(){
        return DONE_FORMAT_RESPONSE;
    }
}
