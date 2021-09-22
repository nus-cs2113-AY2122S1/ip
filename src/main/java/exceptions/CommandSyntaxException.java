package exceptions;

public class CommandSyntaxException extends DukeException{

    public CommandSyntaxException(String message) {
        super("CommandSyntaxException: " + message);
    }
}
