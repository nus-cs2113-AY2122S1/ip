package duke.command;

import duke.ui.Ui;

public class HelpCommand extends Command {

    @Override
    public void executed() {
        Ui.printHelp();
    }
}
