package Command;

import Exception.CommandWrongFormatException;
import Exception.TaskEmptyException;
import Tasks.TaskList;

public abstract class UserCommand {
    TaskList tasks;

    public UserCommand(){}

    public UserCommand (TaskList tasks) {
        this.tasks = tasks;
    }

    abstract public void execute () throws CommandWrongFormatException, TaskEmptyException;

}
