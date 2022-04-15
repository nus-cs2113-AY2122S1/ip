package duke;

public class DukeException extends Exception{
    /**
     * DukeException constructor.
     *
     * @param message Message to be printed, representing which error has occurred.
     */
    public DukeException(String message) {
        super(message);
    }
}
