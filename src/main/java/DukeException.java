public class DukeException {

    public static void printSpaceError() {
        String message = " ☹ OOPS!!! Please check your spacings again.\n";
        Ui.printMessage(message);
    }

    public static void printFormatError() {
        String message = " ☹ OOPS!!! Please check your command format again.\n";
        Ui.printMessage(message);
    }

    public static void printIndexError() {
        String message = " ☹ OOPS!!! Please check that you have entered a valid task number.\n";
        Ui.printMessage(message);
    }

    public static void printCommandError() {
        String message = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        Ui.printMessage(message);
    }

}
