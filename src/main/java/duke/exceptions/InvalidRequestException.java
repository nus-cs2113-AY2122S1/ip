package duke.exceptions;

/**
 * This class is thrown when the input command does not match any available command. It
 * stores the message that tells the user that no command is executed.
 */
public class InvalidRequestException extends DukeException{
    /**
     * Returns a message that tells user the command is invalid
     *
     * @return a string storing the error message
     */
    @Override
    public String getMessage(){
        return String.format("☹ OOPS!!! I can't do that.");
    }
}
