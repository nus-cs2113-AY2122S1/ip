package duke.exceptions;

//used when todo description is in the wrong format
public class TodoException extends Exception {

    public final String TODO_RESPONSE = "The following format must be used: todo [description]";

    @Override
    public String getMessage(){
        return TODO_RESPONSE;
    }
}
