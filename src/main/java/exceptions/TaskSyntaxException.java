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

    public TaskSyntaxException(String cause) {
        super("TaskSyntaxException: " + cause);
    }
}
