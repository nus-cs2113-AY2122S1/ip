package duke.exceptions;


public class StorageFileCreationFailedException extends DukeException {
    public StorageFileCreationFailedException(String filePath) {
        message = "\tThe file '" + filePath + "' cannot be created.";
    }
}
