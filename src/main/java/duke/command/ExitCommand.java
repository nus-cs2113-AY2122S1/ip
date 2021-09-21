package duke.command;

import duke.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        Ui.printBye();
        System.exit(0);
    }
}
