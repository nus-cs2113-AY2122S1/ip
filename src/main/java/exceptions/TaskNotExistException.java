package exceptions;


public class TaskNotExistException extends DukeException {
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task does not exist";
    }

}
