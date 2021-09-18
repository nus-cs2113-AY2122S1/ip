package duke.Exceptions;


/**
 * Thrown when the file to be used for data storage cannot be created.
 */
public class StorageFileCreationFailedException extends DukeException {
    public StorageFileCreationFailedException(String filePath) {
        message = "\tThe file '" + filePath + "' cannot be created.";
    }
}
