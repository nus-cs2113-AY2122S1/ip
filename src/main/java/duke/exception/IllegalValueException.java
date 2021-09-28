package duke.exception;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends DukeException{
    public IllegalValueException(){
        super("IllegalValueException");
    }

    public IllegalValueException(String message){
        super(message);
    }
}
