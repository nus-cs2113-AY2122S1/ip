package duke.exception;

public class InvalidCommandFormatException extends Exception{
    protected final String errorMessage;
    public InvalidCommandFormatException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
