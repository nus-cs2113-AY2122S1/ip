package duke.exceptions;


public class CommandNotFoundException extends DukeException {
    public CommandNotFoundException(String command) {
        message = "\tThe command '" + command + "' is not recognised.";
    }
}
