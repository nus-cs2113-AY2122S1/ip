package duke.exception;

/**
 * Signals that user given input does not fulfill some constraints.
 */
public class InvalidInputException extends DukeException{
    public InvalidInputException(){
        super("InvalidInputException");
    }

    public InvalidInputException(String message){
        super((message));
    }
}
