package duke.exceptions;

public class InvalidRequestException extends DukeException{
    @Override
    public String getMessage(){
        return String.format("☹ OOPS!!! I can't do that.");
    }
}
