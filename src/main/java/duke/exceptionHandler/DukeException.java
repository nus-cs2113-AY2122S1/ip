package duke.exceptionHandler;

/**
 * This is the DukeException Handler and is only targeted at Duke.java class.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class DukeException extends Exception {
    private static final String COMMAND_BORDER = "    ____________________________________________________________";

    public static String openingString = "     ☹ OOPS!!! ";

    /**
     * This calls the superclass Exception constructor and creates the exception
     * object with the error message.
     *
     * @param errorMessage The error message string.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Concatenates the openingString and errorMessage.
     */
    public void printErrorMessage() {
        System.out.println(COMMAND_BORDER);
        System.out.println(openingString + this.getMessage());
        System.out.println(COMMAND_BORDER);
    }
}
