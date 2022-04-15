package duke.validation;

/**
 * This InvalidInputException class is an exception for when
 * an invalid input is received from user.
 */
public class InvalidInputException extends Exception{

    @Override
    public String toString() {
        return "I cannot read that input!";
    }
}
