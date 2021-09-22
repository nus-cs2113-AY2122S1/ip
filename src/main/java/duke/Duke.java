package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.Scanner;

/**
 * To initialize the key objects needed and run the program
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();

        try {
            storage = new Storage();
            storage.loadData();
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
    }

    /**
     * Runs the program until user enters the exit command (bye).
     */
    private void run() {
        Scanner in = new Scanner(System.in);

        while (!Command.isExit()) {
            String input = Parser.getUserInput(in);
            String command = Parser.getFirstWordFromCommand(input);

            try {
                TaskManager.parseUserCommand(command, input, taskList, storage);
            } catch (DukeException e) {
                Ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        new Duke().run();
        Ui.printByeMessage();
    }
}