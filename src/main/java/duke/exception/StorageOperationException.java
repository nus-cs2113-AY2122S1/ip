package duke.exception;

public class StorageOperationException extends DukeException{
    public StorageOperationException(){
        super("StorageOperationException");
    }

    public StorageOperationException(String message){
        super(message);
    }
}
