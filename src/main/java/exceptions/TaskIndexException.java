package exceptions;


public class TaskIndexException extends DukeException {
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task index is empty or is not a number\n";
    }

}
