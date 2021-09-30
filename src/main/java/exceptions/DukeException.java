package exceptions;

/**
 * Abstract Class for all ell exceptions used in this project
 * Every exception will have a message that will be logged to console
 * when it is thrown
 */
public abstract class DukeException extends Exception{

    public static final String EXCEPTION_HEADER ="\uD83D\uDE43 OOPS!!! ";

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return EXCEPTION_HEADER + super.getMessage() ;
    }
}
