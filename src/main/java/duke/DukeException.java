package duke;

public class DukeException {
    protected int errorType;
    private static final int ERROR_TODO_IS_EMPTY = 1;
    private static final int ERROR_EVENT_IS_EMPTY = 2;
    private static final int ERROR_DEADLINE_IS_EMPTY = 3;
    private static final int ERROR_COMMAND_NOT_FOUND = 4;
    private static final int ERROR_IS_INVALID = 5;

    private static final String border = "____________________________________________________________\n";

    public static void printEmptyDescriptionErrorMessage(String command) {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! The description of a " + command + " cannot be empty.");
        System.out.println(border);
    }
    public static void printCommandMismatchErrorMessage() {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(border);
    }
    public static void printCommandIsInvalid() {
        System.out.println(border);
        System.out.println("\uD83D\uDE00 " + "OOPS!!! I'm sorry, the command is invalid.");
        System.out.println(border);
    }
    public DukeException() {
        this.errorType = ERROR_COMMAND_NOT_FOUND;
    }
    public void setErrorType(int type) {
        this.errorType = type;
    }
    public int getErrorType() {
        return errorType;
    }
    public void printError(int type) {
        switch (type) {
            case ERROR_TODO_IS_EMPTY:
                printEmptyDescriptionErrorMessage("todo");
                break;
            case ERROR_EVENT_IS_EMPTY:
                printEmptyDescriptionErrorMessage("event");
                break;
            case ERROR_DEADLINE_IS_EMPTY:
                printEmptyDescriptionErrorMessage("deadline");
                break;
            case ERROR_IS_INVALID:
                printCommandIsInvalid();
                break;
            default:
                printCommandMismatchErrorMessage();
                break;
        }
    }

}


