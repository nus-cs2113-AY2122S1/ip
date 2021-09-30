package duke;

import duke.command.Command;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke class constructor that also loads in tasks data
     * from an external save file
     *
     * @param filePath File path of the external save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method of Duke, which creates a Duke class and runs it.
     *
     * @param args Not applicable.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Executes the main body of Duke.
     */
    public void run() {
        ui.showWelcome();
        handleUserInput();
        ui.showGoodbye();
    }

    /**
     * Reads in user input and performs a corresponding command.
     */
    private void handleUserInput() {
        String userInput;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            userInput = in.nextLine().strip().replaceAll("\\s+"," ");
            try {
                Command command = Parser.parse(userInput);
                command.runCommand(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

}
