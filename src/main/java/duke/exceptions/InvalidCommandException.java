package duke.exceptions;

/**
 * Exception class for when user inputs a command that the programme does not recognise.
 * Extended from DukeException.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Returns error message as a string, prompting user to enter another input.
     * @return error message as a string
     */
    @Override
    public String getMessage() {
        return "Sorry I don't understand that! Can you rephrase?";
    }
}
