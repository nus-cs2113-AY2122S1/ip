package command;

import command.InvalidDoneCommandException;

public class TaskNotExistException extends InvalidDoneCommandException {
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task does not exist";
    }

}
