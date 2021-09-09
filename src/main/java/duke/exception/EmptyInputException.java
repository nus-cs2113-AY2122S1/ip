package duke.exception;

public class EmptyInputException extends Exception {
    public EmptyInputException() {

    }

    public EmptyInputException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Ops! I' am sorry, but your input cannot be empty!";
    }
}
