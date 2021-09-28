package duke.exceptions;

//used when no matching tasks are found from the find command
public class NotFoundException extends Exception {

    public final String NOT_FOUND_RESPONSE = "Kao couldn't find any matching tasks ╮(・ω・;)╭";

    @Override
    public String getMessage(){
        return NOT_FOUND_RESPONSE;
    }
}
