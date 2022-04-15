package duke.exception;

public class DukeException extends Exception {

    /**
     * Class constructor for DukeException
     *
     * @param errorMessage Response message to the corresponding error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
