package exceptions;


/**
 * Thrown to indicate that the file stored previous tasks is crashed, and cannot be restored.
 *
 */
public class FileException extends DukeException{
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The stored data seems to be crashed. Will launch a new list for you\n";
    }
}
