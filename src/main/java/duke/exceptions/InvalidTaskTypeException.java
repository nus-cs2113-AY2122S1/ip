package duke.exceptions;


/**
 * Thrown when the task type entered by the user cannot be recognised or
 * when the task type stored in the data store cannot be recognised.
 */
public class InvalidTaskTypeException extends DukeException {
    public InvalidTaskTypeException(String taskType) {
        message = "\tThe task type '" + taskType + "' is not recognised.\n\tData in data store is corrupted.";
    }
}
