package duke.exception;

public abstract class DukeException extends Throwable {

    /**
     * Indicate what is the error message.
     * @return error message
     */
    public abstract String getMessage();

}

