package duke.Exceptions;


/**
 * Thrown when the file containing the data that are stored cannot be found.
 */
public class DataStoreNotFoundException extends DukeException {
    public DataStoreNotFoundException(String filePath) {
        message = "\tThe file path '" + filePath + "' cannot be found.";
    }
}