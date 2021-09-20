package duke;

import duke.util.Parser;
import duke.util.Ui;

public class Duke {

    private Ui ui;

    public Duke() {
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public static String runCommand(String command) {
        String msg;

        try {
            msg = Parser.parse(command).run();
            return msg;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

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
