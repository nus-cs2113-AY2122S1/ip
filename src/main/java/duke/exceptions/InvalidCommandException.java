package duke.exceptions;

public class InvalidCommandException extends DukeException {

    @Override
    public String getMessage() {
        return "Sorry I don't understand that! Can you rephrase?";
    }
}
