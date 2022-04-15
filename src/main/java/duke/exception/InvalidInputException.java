package duke.exception;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Ops! I am sorry, but I don't know what you are saying )-:";
    }
}
