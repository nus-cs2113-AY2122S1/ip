package exceptions;

public class CommandSyntaxException extends DukeException{

    /**
     * Exception when the input command given by the user has an invalid syntax and cannot be parsed
     * @param message additional message to be displayed when exception thrown
     */
    public CommandSyntaxException(String message) {
        super("CommandSyntaxException: " + message);
    }
}
