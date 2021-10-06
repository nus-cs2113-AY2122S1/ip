package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.Scanner;

public class Patchi {
    public static void main(String[] args) {


        Parser parser = new Parser();
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList tasks = new TaskList();

        storage.loadData(tasks, ui, storage);
        ui.printWelcomeMessage();

        Scanner in = new Scanner(System.in);

        boolean isExit = false;
        while(!isExit) {
            try {
                ui.printTransition();
                String input = in.nextLine();
                Command c = parser.parseCommand(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e){
                ui.println(e.getMessage());
            }
        }

        ui.printGoodbyeMessage();
    }
}
