package duke.exceptions;

//used when user tries to complete an already completed task
public class AlreadyDoneException extends Exception {

    public final String DONE_RESPONSE = "This task is already completed.";

    @Override
    public String getMessage(){
        return DONE_RESPONSE;
    }
}
