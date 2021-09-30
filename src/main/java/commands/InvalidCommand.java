package commands;

import TextUi.TextUi;

public class InvalidCommand extends Command{

    public void execute() {
        TextUi.showInvalidCommandMessage();
    }
}
