package duke.exception;

public class EmptyDoneIndexException extends Exception {
    public EmptyDoneIndexException() {
        super();
    }

    public EmptyDoneIndexException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Ops! You need to add index after 'done' ";
    }
}
