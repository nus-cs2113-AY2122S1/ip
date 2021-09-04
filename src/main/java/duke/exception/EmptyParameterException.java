package duke.exception;

/**
 * To be used when there is a missing parameter in the input string
 */
public class EmptyParameterException extends Exception {

    public EmptyParameterException(String parameterName) {
        System.out.println("The field for " + parameterName + " cannot be empty!");
    }

}
