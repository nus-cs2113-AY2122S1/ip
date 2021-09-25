package duke.exceptions;

/**
 * Abstract class to define the behaviour of all exceptions that Duke can throw
 */
public abstract class DukeException extends Exception {
    protected String errorMessage;

    /**
     * @return errorMessage String of each exception which is different for each DukeException
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
