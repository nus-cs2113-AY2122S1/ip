package commands;

import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DeadlineException;
import exceptions.DeleteException;
import exceptions.DukeException;

public abstract class Command {
    public void execute() throws DeadlineException,
            EventException, TodoException, DeleteException, DoneException, DukeException {
    }
}
