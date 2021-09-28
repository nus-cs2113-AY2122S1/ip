package duke.ErrorHandling;

/**
 * Represent a error resulting from input of user
 */
public class CommandException extends Exception{

    private final String errorMessage;

    /**
     * Constructor for CommandException
     * Create error message of this instance of exception
     *
     * @param errorMessage Error message to print
     */
    public CommandException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    /**
     * Print Error Message
     */
    public void handleException(){
        System.out.println(errorMessage);
    }

    /**
     * Get Error Message of error
     * @return Error Message
     */
    public String getErrorMessage(){
        return errorMessage;
    }
}
