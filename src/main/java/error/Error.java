package error;

/**
 * The Error class manages the display of user facing errors.
 */
public class Error {

    public static final String ERROR_MESSAGE_INVALID_COMMAND = "☹ OOPS!!! I don't know what that means :-(";
    public static final String ERROR_MESSAGE_NOT_A_NUMBER = "☹ OOPS!!! Task number provided is not a number :-(";
    public static final String ERROR_MESSAGE_TASK_NONEXISTENT = "☹ OOPS!!! Task does not exist :-(";
    public static final String ERROR_MESSAGE_TASK_FORMAT = "☹ OOPS!!! Task detail provided is in the wrong format :-(";
    public static final String ERROR_MESSAGE_TASK_NAME_NONEXISTENT = "☹ OOPS!!! Task name cannot be empty :-(";

    public static final String ERROR_MESSAGE_FILE_LOAD_FAIL = "☹ OOPS!!! File failed to load data :-(";
    public static final String ERROR_MESSAGE_FILE_UPDATE_FAIL = "☹ OOPS!!! File failed to update :-(";
    public static final String ERROR_MESSAGE_FILE_ADD_TASK_FAIL = "☹ OOPS!!! Failed to add saved task :-(";
    public static final String ERROR_MESSAGE_FILE_TASK_NAME_NONEXISTENT = "☹ OOPS!!! Saved Task has no name :-(";
    public static final String ERROR_MESSAGE_FILE_TASK_FORMAT = "☹ OOPS!!! Saved Task is in the wrong format :-(";

    /** Displays error when user enters an unrecognised command. */
    public static void displayInvalidCommandError() {
        System.out.println(ERROR_MESSAGE_INVALID_COMMAND);
    }

    /** Displays error when user enters a non-integer value in the command that expects an integer. */
    public static void displayNotANumberError() {
        System.out.println(ERROR_MESSAGE_NOT_A_NUMBER);
    }

    /** Displays error when user tries to access a task that does not exist. */
    public static void displayTaskNonExistentError() {
        System.out.println(ERROR_MESSAGE_TASK_NONEXISTENT);
    }

    /** Displays error when user enters the command in the wrong format. */
    public static void displayTaskFormatError() {
        System.out.println(ERROR_MESSAGE_TASK_FORMAT);
    }

    /** Displays error when user tries to create a task without a task name. */
    public static void displayTaskNameEmptyError() {
        System.out.println(ERROR_MESSAGE_TASK_NAME_NONEXISTENT);
    }

    /** Displays error when the storage file data fail to load on program startup. */
    public static void displayFileLoadError() {
        System.out.println(ERROR_MESSAGE_FILE_LOAD_FAIL);
    }

    /** Displays error when the storage file fails to update. */
    public static void displayFileUpdateError() {
        System.out.println(ERROR_MESSAGE_FILE_UPDATE_FAIL);
    }

    /** Displays error when loading a task from the storage file with an unrecognisable type. */
    public static void displayFileAddTaskError() {
        System.out.println(ERROR_MESSAGE_FILE_ADD_TASK_FAIL);
    }

    /** Displays error when a task loaded from the storage file does not have a task name. */
    public static void displayFileSavedTaskNameEmptyError() {
        System.out.println(ERROR_MESSAGE_FILE_TASK_NAME_NONEXISTENT);
    }

    /** Displays error when loading a task from the storage file has a corrupted format. */
    public static void displayFileSavedTaskFormatError() {
        System.out.println(ERROR_MESSAGE_FILE_TASK_FORMAT);
    }
}
