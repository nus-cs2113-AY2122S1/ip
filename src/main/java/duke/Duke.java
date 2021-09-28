package duke;

import duke.commands.Command;
import duke.utilities.DukeException;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Main entry point of the application and starts the interaction with user
     */
    public void run() {
        ui.printWelcomeMessage();
        startChat();
        ui.printByeMessage();
    }

    private void startChat() {
        Scanner in = new Scanner(System.in);
        boolean isExit = false;

        try {
            do {
                ui.printDivider();
                String input = in.nextLine();
                ui.printDivider();
                Command command = Parser.getCommand(input);
                command.execute(input, tasks, ui, storage);
                isExit = command.isExit(command);
            } while (!isExit);
        } catch (DukeException dukeE){
            dukeE.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
