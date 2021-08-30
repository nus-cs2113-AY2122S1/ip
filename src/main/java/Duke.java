import java.util.Scanner;

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

            msg = parser.parse(command);
            ui.print(msg);
        }

        ui.bye();
    }
}
