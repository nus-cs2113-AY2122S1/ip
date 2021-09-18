package duke.exceptions;


public class DataStoreNotFoundException extends DukeException {
    public DataStoreNotFoundException(String filePath) {
        message = "\tThe file path '" + filePath + "' cannot be found.";
    }
}