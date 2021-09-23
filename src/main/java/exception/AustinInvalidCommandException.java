package exception;

/** Thrown when the user inputs an invalid command.*/
public class AustinInvalidCommandException extends AustinException {
    public AustinInvalidCommandException() {
        super("Sorry. The command is invalid.");
    }

}
