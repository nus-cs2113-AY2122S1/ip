package duke.exception;

/**
 * Signals that some error has occurred while trying to convert and read/write data between the application
 * and the storage file.
 */
public class StorageOperationException extends DukeException{
    public StorageOperationException(){
        super("StorageOperationException");
    }

    public StorageOperationException(String message){
        super(message);
    }
}
