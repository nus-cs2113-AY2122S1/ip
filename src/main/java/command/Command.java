package command;

import duke.DukeException;

public abstract class Command {
    public abstract void run() throws DukeException;
}
