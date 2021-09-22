package commands;

import exceptions.DukeException;
import processors.Ui;

public class InvalidCommand extends Command{
    public Ui ui = new Ui();

    public void execute() throws DukeException {
        throw new DukeException("Unknown Command");
    }
}
