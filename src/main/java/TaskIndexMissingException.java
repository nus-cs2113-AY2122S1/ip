public class TaskIndexMissingException extends InvalidDoneCommand{
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task index cannot be empty";
    }

}
