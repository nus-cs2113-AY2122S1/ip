package duke;

/**
 * Duke Exception class that handles custom exception that occurs during the execution of Duke
 */
public class DukeException extends Exception {

    private String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
