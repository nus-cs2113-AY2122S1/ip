package command;

import command.InvalidDoneCommandException;

public class TaskIndexMissingException extends InvalidDoneCommandException {
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task index cannot be empty";
    }

}
