package exceptions;

public class DukeException extends Exception{

    public static final String EXCEPTION_HEADER ="\uD83D\uDE43 OOPS!!! ";
    
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return EXCEPTION_HEADER + super.getMessage() ;
    }
}
