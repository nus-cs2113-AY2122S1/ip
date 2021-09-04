public class Error {

    public static final String ERROR_MESSAGE_INVALID_COMMAND = "☹ OOPS!!! I don't know what that means :-(";
    public static final String ERROR_MESSAGE_NOT_A_NUMBER = "☹ OOPS!!! Task number provided is not a number :-(";
    public static final String ERROR_MESSAGE_TASK_NONEXISTENT = "☹ OOPS!!! Task does not exist :-(";
    public static final String ERROR_MESSAGE_TASK_FORMAT = "☹ OOPS!!! Task detail provided is in the wrong format :-(";
    public static final String ERROR_MESSAGE_TASK_NAME_NONEXISTENT = "☹ OOPS!!! Task name cannot be empty :-(";

    public static void displayInvalidCommandError() {
        System.out.println(ERROR_MESSAGE_INVALID_COMMAND);
    }

    public static void displayNotANumberError() {
        System.out.println(ERROR_MESSAGE_NOT_A_NUMBER);
    }

    public static void displayTaskNonExistentError() {
        System.out.println(ERROR_MESSAGE_TASK_NONEXISTENT);
    }

    public static void displayTaskFormatError() {
        System.out.println(ERROR_MESSAGE_TASK_FORMAT);
    }

    public static void displayTaskNameEmptyError() {
        System.out.println(ERROR_MESSAGE_TASK_NAME_NONEXISTENT);
    }
}
