package Exceptions;

/**
 * This exception is thrown when the user enters an invalid command
 */
public class UnknownCommandException extends Exception{

    @Override
    public String getMessage() {
        return "Unknown Command Entered. Type \"help\" for a list of available commands.";
    }
}
