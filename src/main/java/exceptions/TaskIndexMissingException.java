package exceptions;


public class TaskIndexMissingException extends DukeException {
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task index cannot be empty\n";
    }

}
