/**
 * Represents all possible Duke errors.
 */
public class DukeException {

    /**
     * Prints an error message when there is a space error in the user's input
     */
    public static void printSpaceError() {
        String message = "☹ OOPS!!! Please check your spacings again!:-)\n";
        Ui.printMessage(message);
    }

    /**
     * Prints an error message when the command entered has an incorrect format.
     */
    public static void printFormatError() {
        String message = "☹ OOPS!!! Please check your command format again! :-)\n";
        Ui.printMessage(message);
    }

    /**
     * Prints an error message when the task number entered is out of scope.
     */
    public static void printIndexError() {
        String message = "☹ OOPS!!! Please check your task number again! :-)\n";
        Ui.printMessage(message);
    }

    /**
     * Prints an error message when an invalid command is entered.
     */
    public static void printCommandError() {
        String message = "☹ OOPS!!! Sorry, I don't know what that means! :-(\n";
        Ui.printMessage(message);
    }

}
