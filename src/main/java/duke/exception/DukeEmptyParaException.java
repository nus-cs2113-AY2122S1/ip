package duke.exception;

/**
 * Signals that the user input, lacking of parameters, does not fulfill the accepted format.
 */
public class DukeEmptyParaException extends DukeException {

    public DukeEmptyParaException() {
        super("EmptyParametersException");
    }

    public DukeEmptyParaException(String message){
        super(message);
    }
}
