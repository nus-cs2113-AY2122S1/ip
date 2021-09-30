package commands;

import ui.TextUi;

public class InvalidCommand extends Command{

    public void execute() {
        TextUi.showInvalidCommandMessage();
    }
}
