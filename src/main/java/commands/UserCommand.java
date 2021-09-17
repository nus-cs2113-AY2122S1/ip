package commands;

import tasks.TaskList;
import exceptions.DukeException;

public abstract class UserCommand {
    TaskList tasks;

    UserCommand(){}

    UserCommand (TaskList tasks) {
        this.tasks = tasks;
    }

    abstract public String execute () throws DukeException;

}
