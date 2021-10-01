package shikabot.command;

import shikabot.ui.TextUi;

public class InvalidCommand extends Command {

    /**
     * Function that prints invalid command message.
     */
    public void execute() {
        TextUi.printUnknownCommandMessage();
    }
}
