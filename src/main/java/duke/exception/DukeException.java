//This class is an exception class to handle errors
package duke.exception;

public class DukeException extends Exception{
    public DukeException(String errorMessage){
        super(errorMessage);
    }
}
