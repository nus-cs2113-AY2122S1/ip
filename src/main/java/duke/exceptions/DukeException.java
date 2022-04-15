package duke.exceptions;

public class DukeException extends Exception {

    /**
     * Throws duke exception if user inputs invalid command
     *
     * @param message message to be printed depending on the type of task and type of error for the user to
     *                rectify and input a valid command that duke can understand.
     */
    public DukeException (String message) {
        super(message);
    }
}
