package duke.exception;

public class IllegalValueException extends DukeException{
    public IllegalValueException(){
        super("IllegalValueException");
    }

    public IllegalValueException(String message){
        super(message);
    }
}
