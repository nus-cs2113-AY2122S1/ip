package duke.command;

import duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute() {
        Ui.printBye();
        System.exit(0);
    }
}
