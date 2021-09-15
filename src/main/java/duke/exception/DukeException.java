package duke.exception;

public class DukeException extends Exception {
    public static class CommandException extends Exception {
        //Throws this exception when the input command is invalid
    }

    public static class StorageException extends Exception {
        //Throws this exception when the data stored in the storage file is invalid
    }
}
