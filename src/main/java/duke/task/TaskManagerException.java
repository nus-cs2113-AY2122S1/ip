package duke.task;

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
