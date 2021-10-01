package command;

import duke.DukeException;
import duke.Ui;

public class CommandHelp extends Command {


    public CommandHelp() {
    }

    /**
     * Prints help command
     */
    @Override
    public void run() {
        Ui.printHelp();
    }
}
