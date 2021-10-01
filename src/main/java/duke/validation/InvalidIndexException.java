package duke.validation;

/**
 * This InvalidIndexException class is an exception for when
 * an invalid input is received from user. Specifically pertaining
 * to input containing a number to be read as an index of a task.
 */
public class InvalidIndexException extends Exception{

    @Override
    public String toString() {
        return "There is not task with that index!";
    }
}
