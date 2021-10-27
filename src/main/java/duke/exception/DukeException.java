//This class is an exception class to handle errors
package duke.exception;

public class DukeException extends Exception{
    /**
     * Exceptions' error messages
     * @param errorMessage The error message that thrown by exceptions
     */
    public DukeException(String errorMessage){
        super(errorMessage);
    }
}
