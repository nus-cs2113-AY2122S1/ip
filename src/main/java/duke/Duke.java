package duke;

import duke.util.Parser;
import duke.util.Ui;

/**
 * Represents the main chat-bot
 *
 */
public class Duke {

    private Ui ui;

    /**
     * Constructor method for <code>Duke</code>
     *
     */
    public Duke() {
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the command returned by the parser
     *
     * @param command user's input command
     * @return the output message as String
     */
    public static String runCommand(String command) {
        String msg;

        try {
            msg = Parser.parse(command).run();
            return msg;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the main program routine
     *
     */
    public void run() {
        boolean isExit = false;
        String command;
        String resultMsg;

        ui.greet();

        while (!isExit) {
            command = ui.readCommand();
            resultMsg = runCommand(command);
            ui.print(resultMsg);
            isExit = Parser.isExit(command);
        }
    }
}
