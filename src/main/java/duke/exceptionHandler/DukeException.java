package duke.exceptionHandler;

/**
 * This is the DukeException Handler and is only targeted at Duke.java class.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class DukeException extends Exception {
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
     * Prints the error message in a formatted fashion by cocatenation.
     */
    public void printErrorMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println(openingString + this.getMessage());
        System.out.println("    ____________________________________________________________");
    }

}
