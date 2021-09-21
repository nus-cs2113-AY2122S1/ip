package duke.command;

import duke.ui.Ui;

public class HelpCommand extends Command {

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        Ui.printHelp();
    }
}
