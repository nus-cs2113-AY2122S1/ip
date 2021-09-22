package commands;

import exceptions.*;

public abstract class Command {
    public void execute() throws DeadlineException,
            EventException, TodoException, DeleteException, DoneException, DukeException {
    }
}
