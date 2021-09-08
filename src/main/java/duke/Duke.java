package duke;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        String command;
        String msg;

        ui.greet();

        while (true) {
            command = ui.readCommand();

            if (parser.isExit(command)) {
                break;
            }

            msg = runCommand(command);
            ui.print(msg);
        }

        ui.bye();
    }

    public static String runCommand(String command) {
        String msg;

        try {
            msg = Parser.parse(command);
            return msg;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
