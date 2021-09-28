package duke.exception;

public class InvalidInputException extends DukeException{
    public InvalidInputException(){
        super("InvalidInputException");
    }

    public InvalidInputException(String message){
        super((message));
    }
}
