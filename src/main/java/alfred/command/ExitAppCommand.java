package alfred.command;

import alfred.ui.TextUi;

public class ExitAppCommand extends Command {
    public void execute() {
        TextUi.shutdownMessage();
    }
}
