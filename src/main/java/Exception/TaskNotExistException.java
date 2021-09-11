package Exception;

public class TaskNotExistException extends InvalidDoneCommandException {
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task does not exist";
    }

}
