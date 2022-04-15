package shima.exception;

public class ShimaException extends Exception {
    public static class StorageException extends Exception {
        //Throws this exception when the data stored in the storage file is invalid
    }
}
