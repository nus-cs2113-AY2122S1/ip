package commands;

import tasks.TaskList;
import exceptions.DukeException;


/**
 * An abstract class to handle all kinds of user commands.
 */
public abstract class UserCommand {
    protected TaskList tasks;

    UserCommand(){}

    UserCommand (TaskList tasks) {
        this.tasks = tasks;
    }

    abstract public String execute () throws DukeException;

}
