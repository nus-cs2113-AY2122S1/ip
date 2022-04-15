package duke.exception;

public class EmptyToDoException extends Exception {
    public EmptyToDoException() {

    }

    public EmptyToDoException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Ops! your todo task cannot be empty!";
    }



}
