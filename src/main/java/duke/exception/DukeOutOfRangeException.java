package duke.exception;

public class DukeOutOfRangeException extends DukeException {

    public DukeOutOfRangeException(){
        super("OutOfRangeException");
    }

    public DukeOutOfRangeException(String message){
        super(message);
    }
}
