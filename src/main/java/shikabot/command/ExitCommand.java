package shikabot.command;

import shikabot.ui.TextUi;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    /**
     * Function that prints the exit message. This function does not exit Shika, that
     * is handled by the run loop itself in Shika.
     */
    public void execute() {
        TextUi.printExitMessage();
    }

}
