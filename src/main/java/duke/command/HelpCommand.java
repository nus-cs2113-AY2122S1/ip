package duke.command;

import duke.ui.Ui;

public class HelpCommand extends Command {

    @Override
    public void execute() {
        Ui.printHelp();
    }
}
