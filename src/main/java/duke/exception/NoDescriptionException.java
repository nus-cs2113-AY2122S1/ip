package duke.exception;
/**
 * Exception to be thrown when attempting to add a task that has no description.
 */
public class NoDescriptionException extends Exception{
    private static final String MESSAGE = "The description of %s cannot be empty!";
    private static final String WHITESPACE_AFTER_COMMAND_REGEX = "\\s+$";

    /**
     * Takes in user input and strips it to the command to be inserted
     * into the exception message and be printed back to user.
     *
     * @param userInput Message that was entered by user.
     */
    public NoDescriptionException(String userInput){
        super(String.format(MESSAGE, userInput.replaceAll(WHITESPACE_AFTER_COMMAND_REGEX, "")));
    }

}
