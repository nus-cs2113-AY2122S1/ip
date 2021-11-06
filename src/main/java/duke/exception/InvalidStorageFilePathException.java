package duke.exception;

/**
 * Signals that the given file path does not fulfill the storage filepath constraints.
 */
public class InvalidStorageFilePathException extends DukeException {
    public InvalidStorageFilePathException(){
        super("InvalidStorageFilePathException");
    }

    public InvalidStorageFilePathException(String message){
        super(message);
    }
}
