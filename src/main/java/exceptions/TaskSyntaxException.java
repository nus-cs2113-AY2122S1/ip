package exceptions;

/**
 * Exception thrown when adding an invalid Task
 * Could be due to missing clauses (e.g. "/by" missing when adding Deadline)
 * OR could be due to empty descriptors
 */
public class TaskSyntaxException extends CommandSyntaxException{

    public static final String MISSING_CLAUSE = "Command is missing required clause : ";
    public static final String EMPTY_AFTER = "Description cannot be empty after: ";
    public static final String EMPTY_BEFORE = "Description cannot be empty before: ";

    /**
     * Exception thrown when a task with invalid syntax is to be added to the task list
     * E.g 'deadline /by' is invalid because there is no datetime given after the '/by' clause
     * @param cause additional message to be displayed when the exception is thrown
     */
    public TaskSyntaxException(String cause) {
        super("TaskSyntaxException: " + cause);
    }
}
