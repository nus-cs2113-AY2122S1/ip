package duke.validation;

/**
 * This InvalidFormatException class is an exception for when
 * an invalid input is received from user. Specifically pertaining
 * to the inputs that have missing information needed to create the
 * task.
 */
public class InvalidFormatException extends Exception{

    @Override
    public String toString() {
        return "You're input is missing some important details!";
    }
}
