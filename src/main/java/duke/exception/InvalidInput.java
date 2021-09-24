package duke.exception;

public class InvalidInput extends DukeException {

    @Override
    public String getMessage() {
        return "I am sorry but your command is invalid. Please give me a valid command.";
    }
}
