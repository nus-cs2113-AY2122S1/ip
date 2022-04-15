package exception;

/** Thrown when the user inputs the valid command in wrong format.*/
public class AustinInvalidCommandFormatException extends AustinException {
    public AustinInvalidCommandFormatException(String command) {
        super("The format of the \"" + command + "\" command is invalid.");
    }
}
