package duke;

/**
 * Custom exception to deal with incorrect input format from user
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Store error message upon constructed
     *
     * @param message
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * To get the message from the instance of the Exception
     *
     * @return error message to be printed
     */
    @Override
    public String getMessage() {
        return message;
    }
}
