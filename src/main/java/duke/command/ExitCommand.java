package duke.command;

import duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void executed() {
        Ui.printBye();
        System.exit(0);
    }
}
