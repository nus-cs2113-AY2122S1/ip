package Exceptions;

public class InsufficientParametersException extends Exception{
    /*  This exception is thrown when the input by the user contains insufficient parameters
        For example, a deadline task without a "/by" or an event without a "/at"
     */
    @Override
    public String getMessage() {
        return "There are insufficient parameters in your input. Type \"help\" for command syntax.";
    }
}
