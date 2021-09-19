package exceptions;

public class FileException extends DukeException{
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The stored data seems to be crashed. Will launch a new list for you\n";
    }
}
