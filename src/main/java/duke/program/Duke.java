package duke.program;

import duke.command.Command;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        LizUi ui = new LizUi();
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        Scanner in = new Scanner(System.in);

        ui.printGreetingMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String line = ui.readCommand(in);
                ui.printLine();
                Command c = parser.parseUserInput(line);
                c.executeCommand(tasks, ui);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.printFileErrorMessage();
            } finally {
                ui.printLine();
            }
        }
    }

}
