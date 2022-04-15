package duke.exception;

public class InvalidEventTimeException extends Exception {
    public InvalidEventTimeException() {
        super();
    }

    public InvalidEventTimeException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Ops! Please add time for Event using the keyword '/at'!";
    }
}
