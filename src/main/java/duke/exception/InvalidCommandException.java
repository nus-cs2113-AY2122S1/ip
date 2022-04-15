package duke.exception;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {

    }

    public InvalidCommandException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Ops! I' m sorry, but I don' t know what it means. )-: ";
    }
}
