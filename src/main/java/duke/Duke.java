package duke;

import duke.command.CommandExecutor;
import duke.ui.Ui;

/**
 * The Duke application serves to aid users in the management of their tasks.
 */
public class Duke {

    /**
     * Provides an interactive prompt to the user.
     */
    private static void interact() {
        String rawLine;
        CommandExecutor commandExecutor = new CommandExecutor();
        Ui ui = new Ui();
        Ui.printWelcomeMessage();

        do {
            rawLine = ui.readInput();
            commandExecutor.execute(rawLine);
        } while (!commandExecutor.isExit());

        Ui.printByeMessage();
    }

    public static void main(String[] args) {
        interact();
    }
}
