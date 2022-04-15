package duke.task;

/**
 * Exception class that handles all TaskManager custom exception during any Task related execution.
 */
public class TaskManagerException extends Exception {

    private String errorMessage;

    public TaskManagerException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
