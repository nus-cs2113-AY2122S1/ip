package command;

import duke.DukeException;
import duke.Ui;

public class CommandHelp extends Command{


    public CommandHelp() {
    }

    @Override
    public void run() throws DukeException {
        Ui.printHelp();
    }
}
