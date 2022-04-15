package duke.common;

/**
 * Common Response Messages to be used across Duke.
 */
public class Messages {

    final public static String ERROR_MESSAGE_TAG = "Error: ";

    final public static String ERROR_MESSAGE_MISSING_ARGUMENTS = ERROR_MESSAGE_TAG + "Invalid input format, missing "
            + "arguments.";
    final public static String ERROR_MESSAGE_EMPTY_ARGUMENTS = ERROR_MESSAGE_TAG + "Invalid input format, empty "
            + "arguments.";
    final public static String ERROR_MESSAGE_UNKNOWN_TASK_TYPE = ERROR_MESSAGE_TAG + "Task type does not exist.";
    final public static String ERROR_MESSAGE_INVALID_DONE = ERROR_MESSAGE_TAG + "done status is not correct.";
    final public static String ERROR_MESSAGE_INVALID_DATE = ERROR_MESSAGE_TAG + "Invalid date input.";
    final public static String ERROR_MESSAGE_INVALID_TASK_NUMBER = ERROR_MESSAGE_TAG + "Task not found.";
    final public static String ERROR_MESSAGE_INVALID_TASK_MANAGER = ERROR_MESSAGE_TAG + "Task Manager not found.";
    final public static String ERROR_MESSAGE_MISSING_KEYWORD = ERROR_MESSAGE_TAG + "keyword is non existent.";
    final public static String ERROR_MESSAGE_UNKNOWN_COMMAND = ERROR_MESSAGE_TAG + "command not found.";

    final public static String ERROR_MESSAGE_FILE_WRITING =
            ERROR_MESSAGE_TAG + "An error has occurred when writing to file %s.";
    final public static String NOTICE_MESSAGE_FILE_NOT_FOUND = "Notice: File %s not found.";

    final public static String MESSAGE_ADD_TASK = "Got it. I've added this task:\n%s";
    final public static String MESSAGE_TOTAL_TASK_NOW = "Now you have %d tasks in the list";

    final public static String MESSAGE_COMMAND_DATE = "The date \"%s\" have:";
    final public static String MESSAGE_COMMAND_BYE = "My time has come...";
    final public static String MESSAGE_COMMAND_FIND = "The keyword \"%s\" you provided found:";
    final public static String MESSAGE_COMMAND_DELETE = "Noted. I've removed this task:";
    final public static String MESSAGE_COMMAND_DONE = "Nice! I've marked this task as done:";
    final public static String MESSAGE_COMMAND_LIST = "Here are the tasks in your list:";

}
