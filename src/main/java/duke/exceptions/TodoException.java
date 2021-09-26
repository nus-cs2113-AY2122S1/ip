package duke.exceptions;

public class TodoException extends Exception {
    public final String TODO_RESPONSE = "The description of todo cannot be empty.";

    @Override
    public String getMessage(){
        return TODO_RESPONSE;
    }
}
