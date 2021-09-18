package duke.Exceptions;


public class InvalidTaskTypeException extends DukeException{
    public InvalidTaskTypeException(String taskType) {
        message = "\tThe task type '" + taskType + "' is not recognised.\n\tData in data store is corrupted.";
    }
}
