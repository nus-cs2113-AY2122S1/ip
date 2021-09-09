package duke;
public class DukeException extends Exception {
    /**
     * Constructor for DukeException
     * @param errorMessage the error message to print
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
