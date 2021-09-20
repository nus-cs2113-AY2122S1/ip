package duke.exceptions;

public class InvalidFilterException extends DukeException {

    @Override
    public String getMessage(){
        return String.format("Input a filter you want to search by");
    }
}
