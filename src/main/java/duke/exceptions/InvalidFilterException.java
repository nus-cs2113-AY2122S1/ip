package duke.exceptions;

/**
 * This class is thrown when the filter word field is missing from the find command. It
 * stores the message that prompts users the correct way to input the code
 */
public class InvalidFilterException extends DukeException {
    /**
     * Returns a message that tells user what is missing in the command
     *
     * @return a string storing the error message
     */
    @Override
    public String getMessage(){
        return "Input a filter you want to search by";
    }
}
