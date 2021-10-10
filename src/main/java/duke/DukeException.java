package duke;

/**
 * To handle all errors that arise when the command passed by a user is illegal.
 */
public class DukeException extends Exception {
    /**
     * Constructor used to create a new deadline object.
     *
     * @param errorMessage the error message to be printed out
     */
    public DukeException(String errorMessage){
        super(errorMessage);
        System.out.println(errorMessage);
    }
}
