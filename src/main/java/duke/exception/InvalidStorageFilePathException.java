package duke.exception;

public class InvalidStorageFilePathException extends DukeException {
    public InvalidStorageFilePathException(){
        super("InvalidStorageFilePathException");
    }

    public InvalidStorageFilePathException(String message){
        super(message);
    }
}
