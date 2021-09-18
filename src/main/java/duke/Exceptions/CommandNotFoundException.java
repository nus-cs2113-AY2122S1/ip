package duke.Exceptions;

/**
 * Thrown when the input command by the user cannot be recognised.
 */
public class CommandNotFoundException extends DukeException {
    public CommandNotFoundException(String command) {
        message = "\tThe command '" + command + "' is not recognised.";
    }
}
