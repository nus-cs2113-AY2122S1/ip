package commands;

import ui.TextUi;

public class InvalidCommand extends Command{

    @Override
    public void execute() {
        TextUi.showInvalidCommandMessage();
    }
}
