package duke.exception;

public class DukeEmptyParaException extends DukeException {

    public DukeEmptyParaException(){
        super("EmptyParametersException");
    }

    public DukeEmptyParaException(String message){
        super(message);
    }
}
