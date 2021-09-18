package duke.exceptions;

public abstract class DukeException extends Exception{
    protected String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
}
