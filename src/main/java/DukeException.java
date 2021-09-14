public class DukeException {

    public static final String SEPARATOR = "____________________________________________________________\n";

    public static void printSpaceError() {
        System.out.println(SEPARATOR
                + " ☹ OOPS!!! Please check your spacings again.\n"
                + SEPARATOR);
    }

    public static void printFormatError() {
        System.out.println(SEPARATOR
                + " ☹ OOPS!!! Please check your command format again.\n"
                + SEPARATOR);
    }

    public static void printInvalidCommandError() {
        System.out.println(SEPARATOR
                + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + SEPARATOR);
    }

    public static void printIndexError() {
        System.out.println(SEPARATOR
                + " ☹ OOPS!!! Please check that you have entered a valid task number.\n"
                + SEPARATOR);
    }

    public static void printMissingParameterError(String error) {
        String errorMessage = "start";
        switch(error) {
        case ("todoDescription"):
            errorMessage = " ☹ OOPS!!! The description of a todo cannot be empty.\n";
            break;
        case ("deadlineBoth"):
            errorMessage = " ☹ OOPS!!! The description/deadline of a deadline cannot be empty.\n";
            break;
        case ("deadlineDeadline"):
            errorMessage = " ☹ OOPS!!! The deadline of a deadline cannot be empty.\n";
            break;
        case ("deadlineDescription"):
            errorMessage = " ☹ OOPS!!! The description of a deadline cannot be empty.\n";
            break;
        case ("eventBoth"):
            errorMessage = " ☹ OOPS!!! The description/event date of an event cannot be empty.\n";
            break;
        case ("eventDate"):
            errorMessage = " ☹ OOPS!!! The event date of an event cannot be empty.\n";
            break;
        case ("eventDescription"):
            errorMessage = " ☹ OOPS!!! The description of an event cannot be empty.\n";
            break;
        default:
            break;
        }
        System.out.println(SEPARATOR
                + errorMessage
                + SEPARATOR);
    }
}
