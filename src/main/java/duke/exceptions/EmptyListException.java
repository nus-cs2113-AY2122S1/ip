package duke.exceptions;

//used when user tries the list command even though list is empty
public class EmptyListException extends Exception {

    public final String EMPTY_LIST_RESPONSE = "The list is empty.";

    @Override
    public String getMessage(){
        return EMPTY_LIST_RESPONSE;
    }
}
