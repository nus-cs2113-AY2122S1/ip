package duke.exception;

/**
 * Signals that the given index is out of range.
 */
public class DukeOutOfRangeException extends DukeException {

    public DukeOutOfRangeException(){
        super("OutOfRangeException");
    }

    public DukeOutOfRangeException(String message){
        super(message);
    }
}
