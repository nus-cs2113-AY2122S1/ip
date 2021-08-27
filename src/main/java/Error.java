public class Error {

    public static final String ERROR_MESSAGE_TASK_NONEXISTENT = "Task does not exist";
    public static final String ERROR_MESSAGE_INVALID_COMMAND = "Command is invalid";
    public static final String ERROR_MESSAGE_TASK_FORMAT = "Task detail provided is in the wrong format";

    public static void displayTaskNonExistentError() {
        System.out.println(ERROR_MESSAGE_TASK_NONEXISTENT);
    }

    public static void displayInvalidCommandError() {
        System.out.println(ERROR_MESSAGE_INVALID_COMMAND);
    }

    public static void displayTaskFormatError() {
        System.out.println(ERROR_MESSAGE_TASK_FORMAT);
    }
}
