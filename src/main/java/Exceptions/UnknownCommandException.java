package Exceptions;

public class UnknownCommandException extends Exception{

    @Override
    public String getMessage() {
        return "Unknown Command Entered. Type \"help\" for a list of available commands.";
    }
}
