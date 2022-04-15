package alfred.command;

import alfred.ui.TextUi;

public class ExitAppCommand extends Command {
    /**
     * This method prints out the app exit message. The app is exited when the loop in Alfred.run() is terminated.
     */
    public void execute() {
        TextUi.shutdownMessage();
    }
}
